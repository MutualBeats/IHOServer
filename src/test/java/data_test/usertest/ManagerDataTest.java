/**
 * @author huangxiao
 * @version 2016年11月26日
 */
package data_test.usertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.userdata.ManagerDataServiceMySqlImpl;
import dataservice.userdataservice.ManagerDataService;
import util.ResultMessage_For_User;

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
		ResultMessage_For_User res1 = null, res2 = null, res3 = null;
		try {
			res1 = managerDataService.find("0000000001", "test");
			res2 = managerDataService.find("1", "test");
			res3 = managerDataService.find("0000000001", "t");
		} catch (Exception e) {
			fail("Exception!");
			e.printStackTrace();
		}
		assertEquals(res1, ResultMessage_For_User.LoginSuccess);
		assertEquals(res2, ResultMessage_For_User.Account_Not_Exist);
		assertEquals(res3, ResultMessage_For_User.PasswordWrong);
	}

}
