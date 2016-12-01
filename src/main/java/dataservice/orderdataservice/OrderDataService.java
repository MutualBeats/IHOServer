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
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Order addOrder(OrderPO po) throws RemoteException;
	/**
	 * 补录订单
	 * 
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Order putUpOrder(String orderID) throws RemoteException;
	/**
	 * 撤销订单
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Order cancelOrder(String orderID) throws RemoteException;
	/**
	 * 执行订单
	 * 
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Order executeOrder(String orderID) throws RemoteException;
	/**
	 * 根据orderId查询订单
	 * 
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public OrderPO findById(String orderID) throws RemoteException;
	/**
	 * 查询用户订单 
	 *  
	 * @param clientID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findByUser(String clientID) throws RemoteException;
	/**
	 * 查询房间订单
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findByRoom(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 用户查询在某酒店订单
	 * 
	 * @param hotelID
	 * @param clientID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUOByHotel(String hotelID, String clientID) throws RemoteException;
	/**
	 * 查询酒店订单
	 * 
	 * @param hotelID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findHotelOrder(String hotelID) throws RemoteException;
	/**
	 * 查找网站未执行订单
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUnexecutedOrder() throws RemoteException;
	
	
}
