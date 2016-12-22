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
