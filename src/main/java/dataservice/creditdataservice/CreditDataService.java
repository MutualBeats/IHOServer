/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.creditdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CreditPO;

public interface CreditDataService extends Remote{
	
	public void insert(CreditPO po) throws RemoteException;
	
	public ArrayList<CreditPO> find(String clientID) throws RemoteException;
	
}
