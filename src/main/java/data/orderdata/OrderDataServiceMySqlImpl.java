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
	public ResultMessage_Order addOrder(OrderPO po) throws RemoteException {
		if(po == null)
			return null;
		
		sqlManager.getConnection();
		
		String sql = "INSERT INTO order_record VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		try {
			params.add(po.getOrderID());
			params.add(po.getOrderState());
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
		} catch (NullPointerException e) {
			// 错误：信息不全
			e.printStackTrace();
			return ResultMessage_Order.Order_Create_Failed;
		}
		
		sql = sqlManager.appendSQL(sql, params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// 添加订单房间、促销策略记录
		addOrderRoom(po.getOrderID(), po.getHotelID(), po.getRoomNumberList());
		addOrderPromotion(po.getOrderID(), po.getPromotionIDList());
		
		return ResultMessage_Order.Order_Create_Successful;
	}
	
	/**
	 * 补录订单
	 */
	@Override
	public ResultMessage_Order putUpOrder(String orderID) throws RemoteException {
		// 错误：订单状态正常
		if(!getOrderState(orderID).equals(OrderState.Exception))
			return ResultMessage_Order.Order_State_Error;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, execute_time=?, finish_time=? WHERE order_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Execute.toString());
		params.add(Time.getCurrentTime());
		// 订单结束时间重新设为空
		params.add("");
		params.add(orderID);
		
		boolean res= sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// 错误：订单号不存在
		if(!res)
			return ResultMessage_Order.Order_Not_Exist;
		
		return ResultMessage_Order.Put_Up_Successful;
	}
 
	/**
	 * 撤销订单
	 */
	@Override
	public ResultMessage_Order cancelOrder(String orderID) throws RemoteException {
		// 错误：订单状态不为未执行
		if (!getOrderState(orderID).equals(OrderState.Unexecuted))
			return ResultMessage_Order.Order_State_Error;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE order_record SET order_state=?, finish_time=? WHERE order_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Canceled.toString());
		params.add(Time.getCurrentTime());
		params.add(orderID);
		
		boolean res = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// 错误：订单号不存在
		if(!res)
			return ResultMessage_Order.Order_Not_Exist;
		
		return ResultMessage_Order.Cancel_Successful;
	}
	
	/**
	 * 执行订单
	 */
	@Override
	public ResultMessage_Order executeOrder(String orderID) throws RemoteException {
		// 错误：订单状态不为未执行
		if (!getOrderState(orderID).equals(OrderState.Unexecuted))
			return ResultMessage_Order.Order_State_Error;

		sqlManager.getConnection();

		String sql = "UPDATE order_record SET order_state=?, execute_time=? WHERE order_id=? ";

		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.Execute.toString());
		params.add(Time.getCurrentTime());
		params.add(orderID);

		boolean res = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		// 错误：订单号不存在
		if (!res)
			return ResultMessage_Order.Order_Not_Exist;

		return ResultMessage_Order.Execute_Successful;
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

	@Override
	public ArrayList<OrderPO> findUnexecutedOrder() throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<OrderPO> orderList = new ArrayList<OrderPO>();
		
		String sql = "SELECT * FROM order_record WHERE order_state=? ORDER BY latest_execute_time DESC";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{OrderState.Unexecuted.toString()});
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
