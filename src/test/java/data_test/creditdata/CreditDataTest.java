/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package data_test.creditdata;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.creditdata.CreditDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import po.CreditPO;
import util.CreditChangeAction;

public class CreditDataTest {
	private CreditDataService creditDataService;
	
	@Before
	public void init() {
		try {
			creditDataService = new CreditDataServiceMySqlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert() {
		CreditPO po = new CreditPO("9999999999", "2016/11/19 17:27:00", CreditChangeAction.RepealOrder, "999999999999", -16, -16);
		try {
			creditDataService.insert(po);
		} catch (Exception e) {
			fail("Exception!");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFind() {
		try {
			ArrayList<CreditPO> creditList = creditDataService.find("1234567890");
			for (CreditPO po : creditList) {
				if(!po.getClientID().equals("1234567890"))
					fail("Find Error!");
				if(po.getChangeTime() == null)
					fail("Null Pointer!");
			}
		} catch (Exception e) {
			fail("Exception!");
			e.printStackTrace();
		}
	}
	
}
