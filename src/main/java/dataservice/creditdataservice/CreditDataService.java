/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.creditdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.credit.CreditPO;
import util.resultmessage.ResultMessage_Credit;

public interface CreditDataService extends Remote{
	/**
	 * Update the credit of user
	 * @param po
	 * @throws RemoteException
	 */
	public ResultMessage_Credit insert(CreditPO po) throws RemoteException;
	/**
	 * Check the Credit Rrecord
	 * @param clientID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<CreditPO> find(String clientID) throws RemoteException;
	
	
}
