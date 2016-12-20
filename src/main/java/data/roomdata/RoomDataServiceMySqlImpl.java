/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.roomdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import data.hoteldata.RoomInfo;
import dataservice.roomdataservice.RoomDataService;
import po.order.OrderPO;
import po.room.RoomPO;
import po.room.RoomRecordPO;
import rmihelper.RoomUpdate;
import util.Time;
import util.hotel.SearchCondition;
import util.resultmessage.ResultMessage_Room;
import util.room.RoomState;
import util.room.RoomType;

public class RoomDataServiceMySqlImpl extends UnicastRemoteObject implements RoomDataService, RoomUpdate, RoomInfo {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
			
	public RoomDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	/**
	 * 房间记录状态枚举类
	 */
	private enum RoomRecordCondition {
		unexecuted, // 未执行
		underway, // 执行中
		done, // 已完成
//		abnormal, // 异常
	}
		
	@Override
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<RoomPO> roomList = new ArrayList<RoomPO>();
		
		String sql = "SELECT * FROM room WHERE hotel_id=? ORDER BY room_type, price, room_number";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID});
		sqlManager.releaseAll();
		
		for(Map<String, Object> map: mapList)
			roomList.add(getRoomPO(map));
		
		return roomList;
	}
	
	/**
	 * 判断房间是否存在
	 * @param hotelID
	 * @param roomNumber
	 * @return boolean
	 */
	private boolean isRoomExists(String hotelID, String roomNumber) {
		sqlManager.getConnection();
		String sql = "SELECT room_number FROM room WHERE hotel_id=? AND room_number=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{hotelID, roomNumber});
		sqlManager.releaseAll();
		
		return map.size() > 0;
	}

	@Override
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException {
		if(po == null)
			return null;
		// 房间已存在
		if(isRoomExists(po.getHotelID(), po.getRoomNumber()))
			return ResultMessage_Room.Room_Exist_Already;
		
		sqlManager.getConnection();

		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getRoomNumber());
		params.add(po.getType().ordinal());
		params.add(po.getPrice());
		params.add(po.getCondition().toString());
		
		String sql = sqlManager.appendSQL("INSERT INTO room VALUES ", params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_Room.Room_Add_Successful;
	}

	@Override
	public RoomPO getRoomInfo(String hotelID, String roomNumber) throws RemoteException {
		if(!isRoomExists(hotelID, roomNumber))
			return null;
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM room WHERE hotel_id=? AND room_number=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{hotelID, roomNumber});
		
		return getRoomPO(map);
	}
	
	/**
	 * 获得房间当前状态
	 * @param hotelID
	 * @param roomNumber
	 * @return RoomCondition
	 */
	private RoomState getCurrentCondition(String hotelID, String roomNumber) {
		sqlManager.getConnection();
		String sql = "SELECT room_condition FROM room WHERE hotel_id=? AND room_number=? ";

		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{hotelID, roomNumber});
		sqlManager.releaseAll();
		// 房间不存在
		if(map.size() == 0)
			return null;
		
		return RoomState.valueOf(map.get("room_condition").toString());
	}
	
	/**
	 * 更新房间当前状态
	 * @param hotelID
	 * @param roomNumber
	 * @param rs
	 * @return boolean
	 */
	private boolean updateRoomState(String hotelID, String roomNumber, RoomState rs) {
		sqlManager.getConnection();
		String sql = "UPDATE room SET room_condition=? WHERE hotel_id=? AND room_number=? ";
		boolean result= sqlManager.executeUpdate(sql, new Object[]{rs.toString(), hotelID, roomNumber});
		sqlManager.releaseAll();
		return result;
	}

	private RoomRecordPO getTodayCheckInRoomRecord(String hotelID, String roomNumber) {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM room_record WHERE hotel_id=? AND room_number=? AND check_in_date=? ";
		Object[] params = new Object[]{hotelID, roomNumber, Time.getCurrentDate()};
		Map<String, Object> map = sqlManager.querySimple(sql, params);
		sqlManager.releaseAll();
		
		return getRoomRecordPO(map);
	}
	
	@Override
	public ResultMessage_Room checkIn(String hotelID, String roomNumber, boolean isOnline) throws RemoteException {
		// 获得房间当前状态
		RoomState currentCondition = getCurrentCondition(hotelID, roomNumber);
		
		// 当天房间已有人住或未被预订，错误
		if(!currentCondition.equals(RoomState.Reserved))
			return ResultMessage_Room.Room_State_Error;
		
		// 若直接操作房间，判断是否为线下入住
		if (!isOnline) {
			RoomRecordPO roomRecordPO = getTodayCheckInRoomRecord(hotelID, roomNumber);
			// 错误：房间记录不存在
			if(roomRecordPO == null)
				return ResultMessage_Room.Room_Record_Not_Exist;
			// 错误：线上入住不可直接操作房间
			if (!roomRecordPO.getOrderID().equals(""))
				return ResultMessage_Room.Not_Offline_Error;
		}
		
		// 房间当天已被预订，更新记录状态为执行中
		sqlManager.getConnection();
		String sql = "UPDATE room_record SET record_condition=? WHERE hotel_id=? AND room_number=? AND check_in_date=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(RoomRecordCondition.underway.toString());
		params.add(hotelID);
		params.add(roomNumber);
		params.add(Time.getCurrentDate());
		
		boolean res = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		if(!res)
			return ResultMessage_Room.Room_Record_Not_Exist;
		
		// 更新房间当前状态
		updateRoomState(hotelID, roomNumber, RoomState.Occupied);
		
		return ResultMessage_Room.Check_In_Successful;
	}
	
	/**
	 * 获取正在执行中的房间记录
	 * @param hotelID
	 * @param roomNumber
	 * @return
	 */
	private RoomRecordPO getUnderWayRoomRecord(String hotelID, String roomNumber) {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM room_record WHERE hotel_id=? AND room_number=? AND record_condition=? ";
		Object[] params = new Object[]{hotelID, roomNumber, RoomRecordCondition.underway.toString()};
		Map<String, Object> map = sqlManager.querySimple(sql, params);
		sqlManager.releaseAll();
		
		return getRoomRecordPO(map);
	}
	
	@Override
	public ResultMessage_Room checkOut(String hotelID, String roomNumber, boolean isOnline) throws RemoteException {
		// 获得房间当前状态
		RoomState currentCondition = getCurrentCondition(hotelID, roomNumber);
	
		// 错误：房间不存在
		if(currentCondition == null)
			return ResultMessage_Room.Room_Not_Exist;	
		// 错误：当天房间未有人住
		if(!currentCondition.equals(RoomState.Occupied))
			return ResultMessage_Room.Room_State_Error;
		
		// 若直接操作房间，判断是否为线下退房
		if(!isOnline) {
			RoomRecordPO roomRecordPO = getUnderWayRoomRecord(hotelID, roomNumber);
			// 错误：房间记录不存在
			if(roomRecordPO == null)
				return ResultMessage_Room.Room_Record_Not_Exist;
			// 错误：线上退房不可直接操作房间
			if(!roomRecordPO.getOrderID().equals(""))
				return ResultMessage_Room.Not_Offline_Error;
		}
		
		// 更新记录（状态更新为已执行同时记录实际离开时间）
		String currentDate = Time.getCurrentDate();
		sqlManager.getConnection();
		String sql = "UPDATE room_record SET record_condition=?, actual_out_date=? WHERE hotel_id=? AND room_number=? AND record_condition=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(RoomRecordCondition.done.toString());
		params.add(currentDate);
		params.add(hotelID);
		params.add(roomNumber);
		params.add(RoomRecordCondition.underway.toString());
		
		boolean res = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		if(!res)
			return ResultMessage_Room.Room_Record_Not_Exist;
		
		// 更新房间当前状态
		updateRoomState(hotelID, roomNumber, RoomState.NotReserved);
		
		return ResultMessage_Room.Check_Out_Successful;
	}

	@Override
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException {
		// 房间不存在
		if(!isRoomExists(hotelID, roomNumber))
			return null;
		
		sqlManager.getConnection();
		
		// TODO 待测试
		String sql = "SELECT * FROM room_record WHERE hotel_id=? AND room_number=? AND estimate_out_date>=? ORDER BY check_in_date";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID, roomNumber, Time.getCurrentDate()});
		
		ArrayList<RoomRecordPO> roomRecordList = new ArrayList<RoomRecordPO>();
		for (Map<String, Object> map : mapList)
			roomRecordList.add(getRoomRecordPO(map));
		
		return roomRecordList;
	}

	@Override
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException {
		// 判断能否添加记录
		ArrayList<RoomRecordPO> recordList = getOrderRecord(po.getHotelID(), po.getRoomNumber());
		if(recordList == null)
			return ResultMessage_Room.Room_Not_Exist;
		
		for (RoomRecordPO each : recordList) {
			// 时间无重叠判断
			if(po.getEstimateCheckOutDate().compareTo(each.getCheckInDate()) < 0
					|| po.getCheckInDate().compareTo(each.getEstimateCheckOutDate()) > 0)
				continue;
			// 时间重叠
			return ResultMessage_Room.Time_Conflict_Error;
		}
		
		// 如果入住时间为当天，改变房间当前状态为已预订
		if(po.getCheckInDate().equals(Time.getCurrentDate()))
			updateRoomState(po.getHotelID(), po.getRoomNumber(), RoomState.Reserved);
		
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getRoomNumber());
		// 房间记录状态默认为未执行
		params.add(RoomRecordCondition.unexecuted.toString());
		params.add(po.getOrderID());
		params.add(po.getCheckInDate());
		params.add(po.getEstimateCheckOutDate());
		// 未离开时实际离开时间为空
		params.add("");
		
		String sql = sqlManager.appendSQL("INSERT INTO room_record VALUES ", params.size());
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_Room.Record_Add_Successful;
	}
	
//	private RoomRecordCondition getRecordCondition(String orderID) {
//		sqlManager.getConnection();
//		String sql = "SELECT record_condition FROM room_record WHERE orderID=? ";
//		RoomRecordCondition res;
//		res = RoomRecordCondition.valueOf(sqlManager.querySimple(sql, new Object[]{orderID}).toString());
//		sqlManager.releaseAll();
//		return res;
//	}
	
	/**
	 * 根据订单号获得房间记录
	 * @param orderID
	 * @return
	 */
	private ArrayList<RoomRecordPO> getRoomRecordByOrderID(String orderID) {
		sqlManager.getConnection();
		ArrayList<RoomRecordPO> roomRecordList = new ArrayList<RoomRecordPO>();
		String sql = "SELECT * FROM room_record WHERE order_id=? ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			roomRecordList.add(getRoomRecordPO(map));
		}
		return roomRecordList;
	}

	@Override
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException {
		ArrayList<RoomRecordPO> roomRecordList = getRoomRecordByOrderID(orderID);
		if(roomRecordList.size() == 0)
			return ResultMessage_Room.Order_Not_Exist;
		// 判断是否需要改变房间状态
		String currentDate = Time.getCurrentDate();
		for (RoomRecordPO each : roomRecordList) {
			if(each.getCheckInDate().equals(currentDate))
				updateRoomState(each.getHotelID(), each.getRoomNumber(), RoomState.NotReserved);
			else
				break;
		}
		
		// 删除房间记录
		sqlManager.getConnection();
		
		String sql = "DELETE FROM room_record WHERE order_id=? ";
		sqlManager.executeUpdate(sql, new Object[]{ orderID });
		sqlManager.releaseConnection();
		
		return ResultMessage_Room.Record_Delete_Successful;
	}
	
	/**
	 * Map转换为RoomPO
	 * @param map
	 * @return RoomPO
	 */
	private RoomPO getRoomPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		RoomPO po = new RoomPO();
		po.setHotelID(map.get("hotel_id").toString());
		po.setRoomNumber(map.get("room_number").toString());
		po.setType(RoomType.values()[Integer.parseInt(map.get("room_type").toString())]);
		po.setPrice(Integer.parseInt(map.get("price").toString()));
		po.setCondition(RoomState.valueOf(map.get("room_condition").toString()));
		return po;
	}
	
	/**
	 * Map转换为RoomRecordPO
	 * @param map
	 * @return RoomRecordPO
	 */
	private RoomRecordPO getRoomRecordPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		RoomRecordPO po = new RoomRecordPO();
		po.setHotelID(map.get("hotel_id").toString());
		po.setRoomNumber(map.get("room_number").toString());
		po.setOrderID(map.get("order_id").toString());
		po.setCheckInDate(map.get("check_in_date").toString());
		po.setEstimateCheckOutDate(map.get("estimate_out_date").toString());
		return po;
	}

	@Override
	public void updateRoomForAbnormalOrder(OrderPO abnormalOrder) {
		// 房间状态置为为未预定，删除房间记录
		try {
			for (String roomNumber : abnormalOrder.getRoomNumberList()) {
				updateRoomState(abnormalOrder.getHotelID(), roomNumber, RoomState.NotReserved);
				deleteRecord(abnormalOrder.getOrderID());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkRoomCondition(String hotelID, SearchCondition sc) throws RemoteException {
		sqlManager.getConnection();
		String sql = "SELECT * FROM room WHERE hotel_id=? AND price>=? AND price<=? ";
		List<Object> params = new ArrayList<>();
		params.add(hotelID);
		params.add(sc.min_price);
		params.add(sc.max_price);
		if(sc.type != RoomType.ALL) {
			sql += " AND room_type=? ";
			params.add(sc.type.ordinal());
		}
		List<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql, params);
		sqlManager.releaseAll();
		
		// 未找到合适房间
		if(mapList.size() == 0)
			return false;
		
		// 判断所选日期能否预订
		if(sc.in_time != null && sc.out_time != null) {
			for (Map<String, Object> map : mapList) {
				ArrayList<RoomRecordPO> roomRecordList = getOrderRecord(hotelID, map.get("room_number").toString());
				boolean isTimeConflict = false;
				for (RoomRecordPO each : roomRecordList) {
					if(sc.out_time.compareTo(each.getCheckInDate()) < 0
						|| sc.in_time.compareTo(each.getEstimateCheckOutDate()) > 0)
						continue;
					else {
						isTimeConflict = true;
						break;
					}
				}
				if(!isTimeConflict)
					return true;
			}
		}
		return false;
	}

	@Override
	public void updateRoomForCheckInOrder(OrderPO todayCheckInOrder) {
		// 更新房间状态为已预订
		for (String roomNumber : todayCheckInOrder.getRoomNumberList()) {
			updateRoomState(todayCheckInOrder.getHotelID(), roomNumber, RoomState.Reserved);
			
		}
	}


}
