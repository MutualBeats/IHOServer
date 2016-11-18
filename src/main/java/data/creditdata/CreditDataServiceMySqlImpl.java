/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.creditdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.creditdataservice.CreditDataService;
import po.CreditPO;

public class CreditDataServiceMySqlImpl extends UnicastRemoteObject implements CreditDataService {

	private static final long serialVersionUID = 2L;

	public CreditDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#insert(po.CreditPO)
	 */
	@Override
	public void insert(CreditPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.creditdataservice.CreditDataService#find(java.lang.String)
	 */
	@Override
	public ArrayList<CreditPO> find(String clientID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
