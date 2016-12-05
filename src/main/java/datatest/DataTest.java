/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import data.userdata.clientdata.ClientDataServiceMySqlImpl;
import po.user.ClientPO;
import util.user.MemberType;

public class DataTest {
	
	public static void main(String[] args) throws Exception {

		
		ClientDataServiceMySqlImpl client = new ClientDataServiceMySqlImpl();
		
		ClientPO po = new ClientPO();
		
		po.setClientID("test");
		po.setClientName("Test");
		po.setContactWay("158");
		po.setCredit(0);
		po.setMemberType(MemberType.Not);
		po.setLevel(0);
		po.setMemberMessage("");
		
		System.out.println(client.regist(po, "password"));
		
//		UtilDataServiceMySqlImpl util = new UtilDataServiceMySqlImpl();
//		
//		System.out.println(util.login("0000000001", "test"));
		
	}

}
