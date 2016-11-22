/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.creditdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CreditPO;
import util.CreditChangeAction;

public class CreditDataServiceImpl_Stub implements CreditDataService {

	@Override
	public void insert(CreditPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public ArrayList<CreditPO> find(String clientID) throws RemoteException {
		System.out.println("Find Succeed!\n");
		ArrayList<CreditPO> creditList = new ArrayList<CreditPO>();
		creditList.add(new CreditPO(clientID, "2016/10/14", CreditChangeAction.ExecuteOrder, "000000000001", 100, 200));
		return creditList;
	}

}
