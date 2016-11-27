/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package data_test.hoteldata;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import data.hoteldata.HotelDataServiceMySqlImpl;
import dataservice.hoteldataservice.HotelDataService;
import po.hotel.HotelEvaluationPO;
import po.hotel.HotelPO;
import util.SearchCondition;

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
			HotelPO po = hotelDataService.getHotelInfo("00000001");
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
		SearchCondition sc = new SearchCondition(null, "如皋市", "如城街道", null, -1, 4);
		try {
			ArrayList<HotelPO> list = hotelDataService.findHotelByCondition(sc);
			if(list.size() != 1 || !list.get(0).getHotelName().equals("锦都金鼎大酒店")) {
				fail("Search Result Error!");
			}
		} catch (Exception e) {
			fail("Exception!");
		}
	}
	
	@Test
	public void testUpdateHotelData() {
		HotelPO po = new HotelPO();
		po.setHotelID("00000001");
		po.setHotelName("锦都金鼎大酒店");
		po.setAddress("如皋市政府对面");
		po.setRegion("如皋市");
		po.setBusinessDistrict("如城街道");
		try {
			hotelDataService.changeHotelInfo(po);
		} catch (Exception e) {
			fail("Exception!");
		}
	}
	
	@Test
	public void testFindHotelEvaluation() {
		try {
			ArrayList<HotelEvaluationPO> list = hotelDataService.getHotelEvaluation("00000001");
			if(list == null)
				fail("Null Pointer!");
		} catch (Exception e) {
			fail("Exception!");
		}
	}
	
	@Test
	public void testEvaluation() {
		HotelEvaluationPO po = new HotelEvaluationPO();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		po.setClientID("0000000001");
		po.setHotelID("00000001");
		po.setEvaluateTime(df.format(new Date()));
		po.setEvaluateScore(4);
		po.setEvaluateInfo("评价测试");
		try {
			hotelDataService.evaluation(po);
		} catch (Exception e) {
			fail("Exception!");
		}
	}
	
	@Test
	public void testInsertHotel() {
		HotelPO po = new HotelPO();
		po.setHotelID("11111111");
		po.setRegion("南京市");
		po.setBusinessDistrict("栖霞区");
		po.setAddress("南大和园");
		po.setHotelName("英尊假日宾馆");
		po.setScore(3.56);
		po.setStarLevel(3);
		try {
			hotelDataService.addHotel(po);
		} catch (RemoteException e) {
			fail("Exception!");
		}
	}
	
}