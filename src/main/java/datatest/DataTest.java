/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;

public class DataTest {
	
	public CreditDataService creditDataService;
	public HotelDataService hotelDataService;
	
	public DataTest() throws Exception {
		creditDataService = new CreditDataServiceMySqlImpl();
		hotelDataService = new HotelDataServiceMySqlImpl();
	}
	
	
	public static void main(String[] args) throws Exception {
		DataTest test = new DataTest();
		HotelPO po = test.hotelDataService.findHotelData("00000001");
		System.out.println(po.getHotelID() + '\t' + po.getRegion() + '\t' + po.getScore());
	}

}
