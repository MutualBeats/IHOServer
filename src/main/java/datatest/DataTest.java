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
import po.HotelPO;

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
		
		HotelPO po = new HotelPO();
		po.setHotelID("11111111");
		po.setRegion("南京市");
		po.setBusinessDistrict("栖霞区");
		po.setAddress("南大和园");
		po.setHotelName("英尊宾馆");
		po.setScore(3.56);
		po.setStarLevel(3);
		
		test.hotelDataService.addHotel(po);
	}

}
