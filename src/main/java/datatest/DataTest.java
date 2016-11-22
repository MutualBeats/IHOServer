/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.userdata.StaffDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.userdataservice.StaffDataService;
import po.CreditPO;

public class DataTest {
	
	public CreditDataService creditDataService;
	public HotelDataService hotelDataService;
	public StaffDataService staffDataService;
	
	public DataTest() throws Exception {
		creditDataService = new CreditDataServiceMySqlImpl();
		hotelDataService = new HotelDataServiceMySqlImpl();
		staffDataService = new StaffDataServiceMySqlImpl();
	}
	
	public static void main(String[] args) throws Exception {
		DataTest test = new DataTest();
		ArrayList<CreditPO> list = test.creditDataService.find("0123456789");
		for (CreditPO po : list) {
			System.out.println(po.getAction());
		}
	}

}
