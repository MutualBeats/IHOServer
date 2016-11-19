/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.ClientDataService;
import po.ClientPO;
import util.ResultMessage;

public class ClientDataServiceMySqlImpl extends UnicastRemoteObject implements ClientDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	public ClientDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ClientDataService#findData(java.lang.String)
	 */
	@Override
	public ClientPO findData(String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ClientDataService#updateData(po.ClientPO)
	 */
	@Override
	public ResultMessage updateData(ClientPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ClientDataService#find(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ClientDataService#insert(po.ClientPO, java.lang.String)
	 */
	@Override
	public ResultMessage insert(ClientPO po, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
