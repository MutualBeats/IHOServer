/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.userdata.StaffDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.userdataservice.StaffDataService;
import po.StaffPO;

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
		StaffPO po = new StaffPO("0000000001", "staff1", "00000001");
		System.out.println(test.staffDataService.updateData(po));
	}

}
