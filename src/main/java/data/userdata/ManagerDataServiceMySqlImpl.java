/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataservice.userdataservice.ManagerDataService;
import po.ClientPO;

public class ManagerDataServiceMySqlImpl extends UnicastRemoteObject implements ManagerDataService {

	private static final long serialVersionUID = 2L;
	
	public ManagerDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientPO findData(String ManagerID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
