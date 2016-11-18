/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;

import dataservice.userdataservice.StaffDataService;
import po.StaffPO;
import util.ResultMessage;

public class StaffDataServiceMySqlImpl implements StaffDataService {

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.StaffDataService#findData(java.lang.String)
	 */
	@Override
	public StaffPO findData(String StaffID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateData(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(StaffPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
