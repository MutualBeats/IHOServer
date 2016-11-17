/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;

import dataservice.userdataservice.ManagerDataService;
import po.ClientPO;
import po.MarketerPO;
import po.StaffPO;
import util.ResultMessage;

public class ManagerDataServiceMySqlImpl implements ManagerDataService {

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#findClientData(java.lang.String)
	 */
	@Override
	public ClientPO findClientData(String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#findStaffData(java.lang.String)
	 */
	@Override
	public StaffPO findStaffData(String StaffID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#updateStaffData(po.StaffPO)
	 */
	@Override
	public ResultMessage updateStaffData(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#insertStaff(po.StaffPO)
	 */
	@Override
	public ResultMessage insertStaff(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#findMarketerData(java.lang.String)
	 */
	@Override
	public MarketerPO findMarketerData(String MarketerID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#updateMarketerData(po.MarketerPO)
	 */
	@Override
	public ResultMessage updateMarketerData(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ManagerDataService#insertMarketer(po.MarketerPO)
	 */
	@Override
	public ResultMessage insertMarketer(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
