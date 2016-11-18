/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.roomdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.databaseutility.SqlManager;
import dataservice.roomdataservice.RoomDataService;
import po.RoomPO;

public class RoomDataServiceMySqlImpl extends UnicastRemoteObject implements RoomDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public RoomDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#find(java.lang.String)
	 */
	@Override
	public ArrayList<RoomPO> find(String HotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#insert(po.RoomPO)
	 */
	@Override
	public void insert(RoomPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#update(po.RoomPO)
	 */
	@Override
	public void update(RoomPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
