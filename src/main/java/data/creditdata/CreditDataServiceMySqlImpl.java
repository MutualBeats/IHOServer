/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.creditdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.creditdataservice.CreditDataService;
import po.CreditPO;

public class CreditDataServiceMySqlImpl implements CreditDataService {

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
