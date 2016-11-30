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

import data.databaseutility.SqlManager;
import dataservice.orderdataservice.OrderDataService;
import po.order.OrderPO;
import util.Time;
import util.order.OrderState;
import util.resultmessage.ResultMessage_Order;

public class OrderDataServiceMySqlImpl extends UnicastRemoteObject implements OrderDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public OrderDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	/**
	 * 判断orderID是否存在
	 * @param orderID
	 * @return boolean
	 */
	private boolean isOrderExists(String orderID) {
		sqlManager.getConnection();
		String sql = "SELECT order_id FROM order_record WHERE order_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{orderID});
		sqlManager.releaseAll();
		
		return map.size() > 0;
	}
	
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
		return OrderState.valueOf(map.get("order_state").toString());
	}

	/**
	 * 补录订单
	 */
	@Override
	public ResultMessage_Order putUpOrder(String orderId) throws RemoteException {
		// 错误：订单状态正常
		if(!getOrderState(orderId).equals(OrderState.Exception))
			return ResultMessage_Order.Put_Up_Failed;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, execute_time=? WHERE order_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Execute);
		params.add(Time.getCurrentTime());
		params.add(orderId);
		
		boolean res= sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// 错误：订单号不存在
		if(!res)
			return ResultMessage_Order.Order_Not_Exist;
		
		return ResultMessage_Order.Put_Up_Successful;
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
	public ResultMessage_Order addOrder(OrderPO po) throws RemoteException {
		if(po == null)
			return null;
		
		sqlManager.getConnection();
		
		String sql = "INSERT INTO order_record VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getOrderId());
		params.add(po.getOrderState());
		params.add(po.getClientId());
		params.add(po.getHotelId());
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
		
		sql = sqlManager.appendSQL(sql, params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// 添加订单房间、促销策略记录
		addOrderRoom(po.getOrderId(), po.getHotelId(), po.getRoomNumberList());
		addOrderPromotion(po.getOrderId(), po.getPromotionIDList());
		
		return ResultMessage_Order.Order_Create_Successful;
	}
 
	/**
	 * 更新订单
	 */
	@Override
	public ResultMessage_Order update(OrderPO po) throws RemoteException {
		if(po == null)
			return null;
		// TODO
		return null;
	}

	/**
	 * 根据orderID查询订单
	 */
	@Override
	public OrderPO findById(String orderId) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM order_record WHERE order_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{orderId});
		sqlManager.releaseAll();
		
		return getOrderPO(map);
	}

	/**
	 * 查询用户订单
	 */
	@Override
	public ArrayList<OrderPO> findByUser(String userId) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> clientOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE client_id=? ORDER BY create_time";
		
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{userId});
		sqlManager.releaseAll();
		
		for (Map<String, Object> map : mapList) {
			clientOrderList.add(getOrderPO(map));
		}
		
		return clientOrderList;
	}

	@Override
	public ArrayList<OrderPO> findByRoom(String hotelId, String roomId) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> roomOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT order_id FROM order_room WHERE hotel_id=? AND room_number=? ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelId, roomId});
		
		for (Map<String, Object> map : mapList) {
			sql = "SELECT * FROM order_record WHERE order_id=? ";
			roomOrderList.add(getOrderPO(sqlManager.querySimple(sql, new Object[]{map.get("order_id")})));
		}

		sqlManager.releaseAll();
		return roomOrderList;
	}

	@Override
	public ArrayList<OrderPO> findUOByHotel(String hotelId, String userId) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> clientHotelOrderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE hotel_id=? AND client_id=? ORDER BY create_time";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelId, userId});
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

	// TODO 接口名修改 findUnexecutedOrder
	@Override
	public ArrayList<OrderPO> findUnexecutedOrder() throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM order_record WHERE order_state=? AND finish_time=? ORDER BY latest_execute_time";
		
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Exception.toString());
		// TODO
//		params.add(Time.getCurrentDate() + " " + "23:59:59");
		
		return null;
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
		
		po.setOrderId(map.get("order_id").toString());
		po.setOrderState(OrderState.valueOf(map.get("order_state").toString()));
		po.setClientId(map.get("client_id").toString());
		po.setHotelId(map.get("hotel_id").toString());
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
		po.setRoomNumberList(getOrderRoom(po.getOrderId()));
		po.setPromotionIDList(getOrderPromotion(po.getOrderId()));
		
		return po;
	}

}
