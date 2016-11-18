/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataservice.userdataservice.StaffDataService;
import po.StaffPO;
import util.ResultMessage;

public class StaffDataServiceMySqlImpl extends UnicastRemoteObject implements StaffDataService {

	private static final long serialVersionUID = 2L;
	
	public StaffDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

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
