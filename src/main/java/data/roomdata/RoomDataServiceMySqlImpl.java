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
import dataservice.roomdataservice.ResultMessage_Room;
import dataservice.roomdataservice.RoomDataService;
import po.RoomPO;
import po.RoomRecordPO;
import util.RoomCondition;
import util.RoomType;
import util.Time;

public class RoomDataServiceMySqlImpl extends UnicastRemoteObject implements RoomDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public RoomDataServiceMySqlImpl() throws RemoteException {
		super();
	}
		
	@Override
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<RoomPO> roomList = new ArrayList<RoomPO>();
		
		String sql = "SELECT * FROM room WHERE hotel_id=? ";
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
		params.add(po.getType().toString());
		params.add(po.getPrice());
		params.add(po.getCondition().toString());
		
		String sql = sqlManager.appendSQL("INSERT INTO room VALUES ", params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		// TODO
		return ResultMessage_Room.Room_Add_Successful;
	}
	
	/**
	 * 获得房间当前状态
	 * @param hotelID
	 * @param roomNumber
	 * @return RoomCondition
	 */
	private RoomCondition getCurrentCondition(String hotelID, String roomNumber) {
		sqlManager.getConnection();
		String sql = "SELECT room_condition FROM room WHERE hotel_id=? AND room_number=? ";

		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{hotelID, roomNumber});
		sqlManager.releaseAll();
		if(map.size() == 0)
			return null;
		return RoomCondition.valueOf(map.get("room_condition").toString());
	}
	
	/**
	 * 更新房间当前状态
	 * @param hotelID
	 * @param roomNumber
	 * @param rc
	 * @return boolean
	 */
	private boolean UpdateRoomCondition(String hotelID, String roomNumber, RoomCondition rc) {
		sqlManager.getConnection();
		String sql = "UPDATE room_condition FROM room WHERE hotel_id=? AND room_number=? ";
		boolean result= sqlManager.executeUpdate(sql, new Object[]{rc.toString(), hotelID, roomNumber});
		sqlManager.releaseAll();
		return result;
	}

	@Override
	public ResultMessage_Room checkIn(String hotelID, String roomNumber) throws RemoteException {
		// 获得房间当前状态
		RoomCondition currentCondition = getCurrentCondition(hotelID, roomNumber);
		
		// 当天房间已有人住或未被预订，错误
		if(!currentCondition.equals(RoomCondition.Reserved))
			return ResultMessage_Room.Check_In_Failed;
				
		// 房间已被预订，更新记录
		sqlManager.getConnection();
		String sql = "UPDATE room_record SET record_condition=? WHERE hotel_id=? AND room_number=? AND check_in_date=? ";
		List<Object> params = new ArrayList<Object>();
		params.add("");
		params.add(hotelID);
		params.add(roomNumber);
		params.add(Time.getCurrentDate());
		boolean result = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		// 未找到订房记录
		if (!result)
			return ResultMessage_Room.Check_In_Failed;
		
		// 更新房间当前状态
		result = UpdateRoomCondition(hotelID, roomNumber, RoomCondition.Occupied);
		if(!result)
			return ResultMessage_Room.Check_In_Failed;
		
		return ResultMessage_Room.Check_In_Successful;
	}
	
	@Override
	public ResultMessage_Room checkOut(String hotelID, String roomNumber) throws RemoteException {
		// 获得房间当前状态
		RoomCondition currentCondition = getCurrentCondition(hotelID, roomNumber);
		
		// 当天房间未有人住，错误
		if(!currentCondition.equals(RoomCondition.Occupied))
			return ResultMessage_Room.Check_Out_Failed;
		
		// 房间原先已有人住，更新记录
		sqlManager.getConnection();
		String sql = "UPDATE room_record SET record_conditon=?, actual_out_date=? WHERE hotel_id=? AND room_number=? ";
		List<Object> params = new ArrayList<Object>();
		params.add("done");
		params.add(Time.getCurrentDate());
		params.add(hotelID);
		params.add(roomNumber);
		boolean result = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		// 未找到订房记录
		if(!result)
			return ResultMessage_Room.Check_Out_Failed;
		
		// 更新房间当前状态
		result = UpdateRoomCondition(hotelID, roomNumber, RoomCondition.NotReserved);
		if(!result)
			return ResultMessage_Room.Check_Out_Failed; 
		
		return ResultMessage_Room.Check_Out_Successful;
	}

	@Override
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException {
		// 房间不存在
		if(!isRoomExists(hotelID, roomNumber))
			return null;
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM room_record WHERE hotel_id=? AND room_number=? AND check_in_date>=? ORDER BY check_in_date";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID, roomNumber, Time.getCurrentDate()});
		
		ArrayList<RoomRecordPO> roomRecordList = new ArrayList<RoomRecordPO>();
		for (Map<String, Object> map : mapList)
			roomRecordList.add(getRoomRecordPO(map));
		
		return roomRecordList;
	}

	@Override
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException {
		if(po == null)
			return ResultMessage_Room.Import_Failed;
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getRoomNumber());
		// 房间记录状态默认为未执行
		params.add("unexecuted");
		// 若为线下入住，订单号为空("")
		params.add(po.getOrderID());
		params.add(po.getCheckInDate());
		params.add(po.getEstimateCheckOutDate());
		// 实际离开时间默认为空
		params.add("");
		
		String sql = sqlManager.appendSQL("INSERT INTO room_record VALUES ", params.size());
		boolean result = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		if(!result)
			return ResultMessage_Room.Import_Failed;
		return ResultMessage_Room.Import_Successful;
	}

	@Override
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "DELETE FROM room_record WHERE order_id=? ";
		boolean result = sqlManager.executeUpdate(sql, new Object[]{ orderID });
		sqlManager.releaseConnection();
		
		if(!result)
			// TODO ResultMessage 添加
			return ResultMessage_Room.Import_Failed;
		
		return ResultMessage_Room.Import_Successful;
	}
	
	private RoomPO getRoomPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		RoomPO po = new RoomPO();
		po.setHotelID(map.get("hotel_id").toString());
		po.setRoomNumber(map.get("room_number").toString());
		po.setType(RoomType.valueOf(map.get("room_type").toString()));
		po.setPrice(Integer.parseInt(map.get("price").toString()));
		po.setCondition(RoomCondition.valueOf(map.get("condition").toString()));
		return po;
	}
	
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
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
