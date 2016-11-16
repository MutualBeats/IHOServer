/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.roomdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.roomdataservice.RoomDataService;
import po.RoomPO;

public class RoomDataServiceMySqlImpl implements RoomDataService {

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
