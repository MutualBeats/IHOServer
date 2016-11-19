/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelEvaluationPO;

public class DataTest {
	
	public CreditDataService creditDataService;
	public HotelDataService hotelDataService;
	
	public DataTest() throws Exception {
		creditDataService = new CreditDataServiceMySqlImpl();
		hotelDataService = new HotelDataServiceMySqlImpl();
	}
	
	
	public static void main(String[] args) throws Exception {
		DataTest test = new DataTest();
		ArrayList<HotelEvaluationPO> list = test.hotelDataService.findHotelEvaluation("00000001");
		System.out.println(list.size());
	}

}
