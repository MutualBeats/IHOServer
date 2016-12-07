/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import data.hoteldata.HotelDataServiceMySqlImpl;
import po.hotel.HotelPO;

public class DataTest {
	
	public static void main(String[] args) throws Exception {
		HotelDataServiceMySqlImpl hotel = new HotelDataServiceMySqlImpl();
		
		HotelPO po = new HotelPO();
		po.setHotelName("测试");
		po.setAddress("测试数据库酒店id自增");
		po.setRegion("测试地区");
		po.setBusinessDistrict("测试商圈");
		po.setScore(4);
		po.setStarLevel(0);
		
		System.out.println(hotel.addHotel(po));
	}

}
