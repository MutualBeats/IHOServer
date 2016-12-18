/**
 * @author huangxiao
 * @version 2016年11月26日
 */
package data_test.usertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import data.userdata.marketerdata.MarketerDataServiceMySqlImpl;
import dataservice.userdataservice.MarketerDataService;
import po.user.MarketerPO;
import util.resultmessage.ResultMessage_User;

public class MarketerDataTest {
	private MarketerDataService marketerDataService;

	@Before
	public void init() {
		try {
			marketerDataService = new MarketerDataServiceMySqlImpl();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	@Test
	public void testFind() {
		ResultMessage_User res1 = null, res2 = null, res3 = null;
		try {
			res1 = marketerDataService.find("0000000001", "test");
			res2 = marketerDataService.find("1", "test");
			res3 = marketerDataService.find("0000000001", "t");
		} catch (Exception e) {
			fail("Exception!");
			e.printStackTrace();
		}
		assertEquals(res1, ResultMessage_User.LoginSuccess);
		assertEquals(res2, ResultMessage_User.Account_Not_Exist);
		assertEquals(res3, ResultMessage_User.PasswordWrong);
	}

	@Test
	public void testFindData() {
		MarketerPO po = null;
		try {
			po = marketerDataService.getMarketerInfo("0000000001");
		} catch (Exception e) {
			fail("Exception!");
			e.printStackTrace();
		}
		assertEquals(po.getContactWay(), "110");
	}

}
