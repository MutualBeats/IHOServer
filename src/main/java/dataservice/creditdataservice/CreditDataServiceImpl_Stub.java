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
	public ResultMessage_CreditData insert(CreditPO po) throws RemoteException {
		System.out.print("Insert Succeed!\r\n");
		return ResultMessage_CreditData.Update_Successful;
	}

	@Override
	public ArrayList<CreditPO> find(String clientID) throws RemoteException {
		System.out.print("Find Succeed!\r\n");
		ArrayList<CreditPO> creditList = new ArrayList<CreditPO>();
		creditList.add(new CreditPO(clientID, "2016/10/16 11:30:24", CreditChangeAction.ExecuteOrder, "100000000000", 100, 300));
		creditList.add(new CreditPO(clientID, "2016/10/14 06:20:30", CreditChangeAction.ExecuteOrder, "200000000000", 100, 200));
		return creditList;
	}

}
