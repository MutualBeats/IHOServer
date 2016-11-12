package dataservice.orderdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrderPO;
import util.ResultMessage;

public interface OrderDataService {
	/**
	 * 补录订单
	 * 
	 * @param orderId
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage putUpOrder(String orderId) throws RemoteException; 
	/**
	 * 添加订单
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage insert(OrderPO po) throws RemoteException;
	/**
	 * 更新订单
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage update(OrderPO po) throws RemoteException;
	/**
	 * 根据orderId查询订单
	 * 
	 * @param orderId
	 * @return
	 * @throws RemoteException
	 */
	public OrderPO findById(String orderId) throws RemoteException;
	/**
	 * 查询用户订单 
	 *  
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findByUser(String userId) throws RemoteException;
	/**
	 * 查询房间订单
	 * 
	 * @param hotelId
	 * @param roomId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findByRoom(String hotelId, String roomId) throws RemoteException;
	/**
	 * 用户查询在某酒店订单
	 * 
	 * @param hotelId
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUOByHotel(String hotelId, String userId) throws RemoteException;
	/**
	 * 查询酒店订单
	 * 
	 * @param hotelId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findHotelOrder(String hotelId) throws RemoteException;
	/**
	 * 查找网站未执行订单
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderPO> findUnexcutedOrder() throws RemoteException;
}
