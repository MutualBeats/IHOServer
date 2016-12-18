/**
 * @author huangxiao
 * @version 2016年11月26日
 */
package data_test.usertest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import data.userdata.managerdata.ManagerDataServiceMySqlImpl;
import dataservice.userdataservice.ManagerDataService;
import po.user.ManagerPO;

public class ManagerDataTest {
	private ManagerDataService managerDataService;

	@Before
	public void init() {
		try {
			managerDataService = new ManagerDataServiceMySqlImpl();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	@Test
	public void testFind() {
		ManagerPO po;
		try {
			po = managerDataService.getManagerInfo();
			assertEquals("manager_ac", po.getManagername());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
