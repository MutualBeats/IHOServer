/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import po.CreditPO;

public class DataTest {
	
	public CreditDataService creditDataService;
	public HotelDataService hotelDataService;
	
	public DataTest() throws Exception {
		creditDataService = new CreditDataServiceMySqlImpl();
		hotelDataService = new HotelDataServiceMySqlImpl();
	}
	
	
	public static void main(String[] args) throws Exception {
		DataTest test = new DataTest();
		CreditPO po = new CreditPO("0123456789", "2017/01/01 23:15:00", 128, 96);
		test.creditDataService.insert(po);
	}

}
