/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataservice.userdataservice.MarketerDataService;
import po.MarketerPO;
import util.ResultMessage;

public class MarketerDataServiceMySqlImpl extends UnicastRemoteObject implements MarketerDataService {

	private static final long serialVersionUID = 2L;
	
	public MarketerDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.MarketerDataService#findData(java.lang.String)
	 */
	@Override
	public MarketerPO findData(String MarketerID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateData(MarketerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultMessage insert(MarketerPO po,String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
