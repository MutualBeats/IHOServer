/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.orderdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.ID;
import data.databaseutility.SqlManager;
import dataservice.orderdataservice.OrderDataService;
import po.order.OrderPO;
import util.Time;
import util.order.OrderState;
import util.resultmessage.ResultMessage_Order;

public class OrderDataServiceMySqlImpl extends UnicastRemoteObject implements OrderDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	private static final int ORDER_ID_LENGTH = 16;
	
	public OrderDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
//	/**
//	 * 判断orderID是否存在
//	 * @param orderID
//	 * @return boolean
//	 */
//	private boolean isOrderExists(String orderID) {
//		sqlManager.getConnection();
//		String sql = "SELECT order_id FROM order_record WHERE order_id=? ";
//		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{orderID});
//		sqlManager.releaseAll();
//		
//		return map.size() > 0;
//	}
	
	/**
	 * 获得订单状态
	 * @param orderID
	 * @return OrderState
	 */
	private OrderState getOrderState(String orderID) {
		sqlManager.getConnection();
		String sql = "SELECT order_state FROM order_record WHERE order_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		// 订单号不存在
		if(map.size() == 0)
			return null;
		return OrderState.valueOf(map.get("order_state").toString());
	}
	
	/**
	 * 添加订单预订房间记录
	 * @param hotelID
	 * @param roomNumberList
	 * @return boolean
	 */
	private boolean addOrderRoom(String orderID, String hotelID, List<String> roomNumberList) {
		sqlManager.getConnection();
		String sql = "INSERT INTO order_room VALUES (?,?,?)";
		
		for (String roomNumber : roomNumberList) {
			Object[] params = new Object[]{orderID, hotelID, roomNumber};
			if(!sqlManager.executeUpdate(sql, params)) {
				sqlManager.releaseConnection();
				return false;
			}
		}
		
		sqlManager.releaseConnection();
		return true;
	}
	
	/**
	 * 添加订单使用促销策略记录
	 * @param promotionIDList
	 * @return boolean
	 */
	private boolean addOrderPromotion(String orderID, List<String> promotionIDList) {
		sqlManager.getConnection();
		
		String sql = "INSERT INTO order_promotion VALUES (?,?)";
		for (String promotionID : promotionIDList) {
			Object[] params = new Object[]{orderID, promotionID};
			if(!sqlManager.executeUpdate(sql, params)) {
				sqlManager.releaseConnection();
				return false;
			}
		}
		
		sqlManager.releaseConnection();
		return true;
	}
	

	/**
	 * 生成订单
	 */
	@Override
	public String addOrder(OrderPO po) throws RemoteException {
		sqlManager.getConnection();
		
		String sql;
		// 生成order_record记录
		List<Object> params = new ArrayList<Object>();
		params.add(null);
		// order_id待生成
		params.add("");
		params.add(po.getOrderState().toString());
		params.add(po.getClientID());
		params.add(po.getHotelID());
		params.add(po.getValue());
		params.add(po.getMakeTime());
		params.add(po.getLatestETime());
		// 订单执行时间初始为空
		params.add("");
		// 订单结束时间初始为空
		params.add("");
		params.add(po.getCheckInDate());
		params.add(po.getEstimateCheckOutDate());
		// 实际离开日期初始为空
		params.add("");
		params.add(po.getNumOfPeople());
		params.add(po.isChildren());
		
		sql = sqlManager.appendSQL("INSERT INTO order_record VALUES ", params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 获取数据库自增id
		sql = "SELECT id FROM order_record WHERE order_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[] { "" });
		int id = Integer.parseInt(map.get("id").toString());
		// 设置order_id
		sql = "UPDATE order_record SET order_id=? WHERE id=? ";
		String orderID = ID.idToString(id, ORDER_ID_LENGTH);
		sqlManager.executeUpdate(sql, new Object[] { orderID, id });
		
		sqlManager.releaseAll();
		
		// 添加订单房间、促销策略记录
		addOrderRoom(orderID, po.getHotelID(), po.getRoomNumberList());
		addOrderPromotion(orderID, po.getPromotionIDList());
		
		return orderID;
	}
	
	/**
	 * 补登记执行订单
	 */
	@Override
	public ResultMessage_Order putUpOrder(String orderID) throws RemoteException {
		OrderState state = getOrderState(orderID);
		// 错误：订单不存在
		if(state == null)
			return ResultMessage_Order.Order_Not_Exist;
		// 错误：订单状态不为异常
		if(!state.equals(OrderState.Exception))
			return ResultMessage_Order.Order_State_Error;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, execute_time=?, finish_time=? WHERE order_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Execute.toString());
		params.add(Time.getCurrentTime());
		// 订单结束时间重新设为空
		params.add("");
		params.add(orderID);
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_Order.Put_Up_Successful;
	}
 
	/**
	 * 撤销订单
	 */
	@Override
	public ResultMessage_Order cancelOrder(String orderID) throws RemoteException {
		OrderState state = getOrderState(orderID);
		// 错误：订单不存在
		if(state == null)
			return ResultMessage_Order.Order_Not_Exist;
		// 错误：订单状态不为未执行
		if(!state.equals(OrderState.Unexecuted))
			return ResultMessage_Order.Order_State_Error;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, finish_time=? WHERE order_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Canceled.toString());
		params.add(Time.getCurrentTime());
		params.add(orderID);
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_Order.Cancel_Successful;
	}
	
	/**
	 * 执行订单
	 */
	@Override
	public ResultMessage_Order executeOrder(String orderID) throws RemoteException {
		OrderState state = getOrderState(orderID);
		// 错误：订单不存在
		if(state == null)
			return ResultMessage_Order.Order_Not_Exist;
		// 错误：订单状态不为未执行
		if(!state.equals(OrderState.Unexecuted))
			return ResultMessage_Order.Order_State_Error;

		sqlManager.getConnection();

		String sql = "UPDATE order_record SET order_state=?, execute_time=? WHERE order_id=? ";

		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Execute.toString());
		params.add(Time.getCurrentTime());
		params.add(orderID);

		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		return ResultMessage_Order.Execute_Successful;
	}
	
	/**
	 * 完成订单
	 */
	@Override 
	public ResultMessage_Order finishOrder(String orderID) throws RemoteException {
		OrderState state = getOrderState(orderID);
		// 错误：订单不存在
		if(state == null)
			return ResultMessage_Order.Order_Not_Exist;
		// 错误：订单状态不为执行中
		if(!state.equals(OrderState.Execute))
			return ResultMessage_Order.Order_State_Error;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, finish_time=?, actual_out_date=? WHERE order_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Finished.toString());
		params.add(Time.getCurrentTime());
		params.add(Time.getCurrentDate());
		params.add(orderID);
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		return ResultMessage_Order.Finish_Successful;
	}

	/**
	 * 根据orderID查询订单
	 */
	@Override
	public OrderPO findById(String orderID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM order_record WHERE order_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		
		return getOrderPO(map);
	}

	/**
	 * 查询用户订单
	 */
	@Override
	public ArrayList<OrderPO> findByUser(String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> clientOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE client_id=? ORDER BY create_time";
		
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{clientID});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			clientOrderList.add(getOrderPO(map));
		}
		
		return clientOrderList;
	}

	@Override
	public ArrayList<OrderPO> findByRoom(String hotelID, String roomNumber) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> roomOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT order_id FROM order_room WHERE hotel_id=? AND room_number=? ORDER BY check_in_date DESC";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID, roomNumber});
		
		for (Map<String, Object> map : mapList) {
			sql = "SELECT * FROM order_record WHERE order_id=? ";
			roomOrderList.add(getOrderPO(sqlManager.querySimple(sql, new Object[]{map.get("order_id")})));
		}

		sqlManager.releaseAll();
		return roomOrderList;
	}

	@Override
	public ArrayList<OrderPO> findUOByHotel(String hotelID, String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> clientHotelOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE hotel_id=? AND client_id=? ORDER BY create_time";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID, clientID});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			clientHotelOrderList.add(getOrderPO(map));
		}
		
		return clientHotelOrderList;
	}

	@Override
	public ArrayList<OrderPO> findHotelOrder(String hotelId) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> hotelOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE hotel_id=? ORDER BY create_time";
		
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelId});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			hotelOrderList.add(getOrderPO(map));
		}
		
		return hotelOrderList;
		
	}

	/**
	 * 获得某天未执行订单列表
	 */
	@Override
	public ArrayList<OrderPO> findUnexecutedOrder(String date) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		// TODO 时间判断
		String sql = "SELECT * FROM order_record "
				+ "WHERE (order_state=? AND check_in_date=?) OR (order_state=? AND finish_time>?) "
				+ "ORDER BY latest_execute_time DESC";
		
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Unexecuted.toString());
		params.add(date);
		params.add(OrderState.Exception.toString());
		params.add(date);
		
		List<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql, params);
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			orderList.add(getOrderPO(map));
		}
		
		return orderList;
	}
	
	/**
	 * 获得订单预订房间号列表
	 * @param orderID
	 * @return roomNumberList
	 */
	private ArrayList<String> getOrderRoom(String orderID) {
		ArrayList<String> roomNumberList = new ArrayList<String>();
		
		sqlManager.getConnection();
		String sql = "SELECT room_number FROM order_room WHERE order_id=? ORDER BY room_number";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			roomNumberList.add(map.get("room_number").toString());
		}
		
		return roomNumberList;
	}
	
	/**
	 * 获得订单使用促销策略id列表
	 * @param orderID
	 * @return promotionIDList
	 */
	private ArrayList<String> getOrderPromotion(String orderID) {
		ArrayList<String> promotionIDList = new ArrayList<String>();
		
		sqlManager.getConnection();
		String sql = "SELECT promotion_id FROM order_promotion WHERE order_id=? ORDER BY promotion_id";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			promotionIDList.add(map.get("promotion_id").toString());
		}
		
		return promotionIDList;
	}
	
	/**
	 * Map转换为OrderPO
	 * @param map
	 * @return OrderPO
	 */
	private OrderPO getOrderPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		
		OrderPO po = new OrderPO();
		
		po.setOrderID(map.get("order_id").toString());
		po.setOrderState(OrderState.valueOf(map.get("order_state").toString()));
		po.setClientID(map.get("client_id").toString());
		po.setHotelID(map.get("hotel_id").toString());
		po.setValue(Double.parseDouble(map.get("value").toString()));
		po.setMakeTime(map.get("create_time").toString());
		po.setLatestETime(map.get("latest_execute_time").toString());
		po.setExecuteTime(map.get("execute_time").toString());
		po.setFinishTime(map.get("finish_time").toString());
		po.setCheckInDate(map.get("check_in_date").toString());
		po.setEstimateCheckOutDate(map.get("estimate_out_date").toString());
		po.setActualCheckOutDate(map.get("actual_out_date").toString());
		po.setNumOfPeople(Integer.parseInt(map.get("num_of_people").toString()));
		po.setChildren(Boolean.parseBoolean(map.get("children").toString()));
		// 获得订单房间列表和促销策略信息
		po.setRoomNumberList(getOrderRoom(po.getOrderID()));
		po.setPromotionIDList(getOrderPromotion(po.getOrderID()));
		
		return po;
	}

}
