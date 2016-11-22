/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.orderdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.databaseutility.SqlManager;
import dataservice.orderdataservice.OrderDataService;
import po.OrderPO;
import util.ResultMessage;

public class OrderDataServiceMySqlImpl extends UnicastRemoteObject implements OrderDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public OrderDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#putUpOrder(java.lang.String)
	 */
	@Override
	public ResultMessage putUpOrder(String orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#insert(po.OrderPO)
	 */
	@Override
	public ResultMessage insert(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#update(po.OrderPO)
	 */
	@Override
	public ResultMessage update(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findById(java.lang.String)
	 */
	@Override
	public OrderPO findById(String orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findByUser(java.lang.String)
	 */
	@Override
	public ArrayList<OrderPO> findByUser(String userId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findByRoom(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<OrderPO> findByRoom(String hotelId, String roomId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findUOByHotel(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<OrderPO> findUOByHotel(String hotelId, String userId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findHotelOrder(java.lang.String)
	 */
	@Override
	public ArrayList<OrderPO> findHotelOrder(String hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.orderdataservice.OrderDataService#findUnexcutedOrder()
	 */
	@Override
	public ArrayList<OrderPO> findUnexcutedOrder() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
