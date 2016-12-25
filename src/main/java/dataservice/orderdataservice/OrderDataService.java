package dataservice.orderdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.order.OrderPO;
import util.resultmessage.ResultMessage_Order;

public interface OrderDataService extends Remote{
	 
	/**
	 * 添加订单
	 * 
	 * @param po
	 * @return order_id
	 * @throws RemoteException
	 */
	public String addOrder(OrderPO po) throws RemoteException;
	/**
	 * 补录订单
	 * 
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order putUpOrder(String orderID) throws RemoteException;
	/**
	 * 申诉订单
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order appealOrder(String orderID) throws RemoteException;
	/**
	 * 撤销订单
	 * 
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order cancelOrder(String orderID) throws RemoteException;
	/**
	 * 执行订单
	 * 
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order executeOrder(String orderID) throws RemoteException;
	/**
	 * 完成订单
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order finishOrder(String orderID) throws RemoteException;
	/**
	 * 根据orderId查询订单
	 * 
	 * @param orderID
	 * @return OrderPO
	 * @throws RemoteException
	 */
	public OrderPO findById(String orderID) throws RemoteException;
	/**
	 * 查询用户订单 
	 *  
	 * @param clientID
	 * @return ArrayList<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findByUser(String clientID) throws RemoteException;
	/**
	 * 用户查询在某酒店订单
	 * 
	 * @param hotelID
	 * @param clientID
	 * @return ArrayList<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUOByHotel(String hotelID, String clientID) throws RemoteException;
	/**
	 * 查询酒店订单
	 * 
	 * @param hotelID
	 * @return ArrayList<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findHotelOrder(String hotelID) throws RemoteException;
	/**
	 * 查找网站某天未执行订单
	 * 
	 * @return ArrayList<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUnexecutedOrder(String date) throws RemoteException;
	/**
	 * 查找网站异常订单
	 * 
	 * @return ArrayList<OrderPO>
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findAbnormalOrder() throws RemoteException;
	/**
	 * 更新订单为已评价状态
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Order orderEvaluate(String orderID) throws RemoteException;
	
	
}
