/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package data_test.hoteldata;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;

public class HotelDataTest {
	private HotelDataService hotelDataService;
	
	@Before
	public void init() {
		try {
			hotelDataService = new HotelDataServiceMySqlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindHotelData() {
		try {
			HotelPO po = hotelDataService.findHotelData("00000001");
			if(!po.getHotelName().equals("锦都金鼎大酒店") || 
					!po.getRegion().equals("如皋市") || 
					!po.getBusinessDistrict().equals("如城街道")) {
				fail("Find Error!");
			}
		} catch (Exception e) {
			fail("Exception!");
		}
	}
	
	@Test
	public void testFindHotelListData() {
		// TODO
	}
	
	@Test
	public void testUpdateHotelData() {
		// TODO
	}
	
	@Test
	public void testFindHotelEvaluation() {
		// TODO
	}
	
	@Test
	public void testInsertHotelEvaluation() {
		// TODO
	}
	
	@Test
	public void testInsertHotel() {
		// TODO
	}
	
}