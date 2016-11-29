/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.creditdataservice;

import java.rmi.RemoteException;

import po.credit.CreditPO;
import util.credit.CreditChangeAction;

public class CreditDataServiceImpl_Driver {
	
	public void drive(CreditDataService creditDataService) throws RemoteException {
		CreditPO po = new CreditPO("0000000001", "2016/10/14 10:00:00", CreditChangeAction.ExecuteOrder, "000000000001", 100, 200);
		
		creditDataService.insert(po);
		creditDataService.find("0000000001");
	}

}
