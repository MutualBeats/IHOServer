/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.userdata;

import java.rmi.RemoteException;

import dataservice.userdataservice.UserDataService;
import po.ClientPO;
import po.MarketerPO;
import po.StaffPO;

public class UserDataServiceMySqlImpl implements UserDataService {

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findClientData(java.lang.String)
	 */
	@Override
	public ClientPO findClientData(String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findStaffData(java.lang.String)
	 */
	@Override
	public StaffPO findStaffData(String StaffID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findMarketerData(java.lang.String)
	 */
	@Override
	public MarketerPO findMarketerData(String MarketerID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#updateClientData(po.ClientPO)
	 */
	@Override
	public void updateClientData(ClientPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#updateStaffData(po.StaffPO)
	 */
	@Override
	public void updateStaffData(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#updateMarketerData(po.MarketerPO)
	 */
	@Override
	public void updateMarketerData(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#insertMarketer(po.MarketerPO)
	 */
	@Override
	public void insertMarketer(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#insertStaff(po.StaffPO)
	 */
	@Override
	public void insertStaff(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#findClient(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean findClient(String ID, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.UserDataService#insertClient(po.ClientPO, java.lang.String)
	 */
	@Override
	public void insertClient(ClientPO po, String password) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
