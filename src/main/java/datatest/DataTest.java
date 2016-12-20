/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.hoteldata.HotelDataServiceMySqlImpl;
import po.hotel.HotelPO;
import util.hotel.SearchCondition;
import util.room.RoomType;

public class DataTest {
	
	public static void main(String[] args) throws Exception {
		HotelDataServiceMySqlImpl hotel = new HotelDataServiceMySqlImpl();
		
		SearchCondition searchCondition =  new SearchCondition(
				null, 
				"江苏省 南京市 南京市", 
				"鼓楼商圈", 
				null, 
				5, 
				0, 
				null, null, 
				0, Integer.MAX_VALUE, 
				RoomType.ALL, 
				false);
		
		ArrayList<HotelPO> hotelList = hotel.findHotelByCondition(searchCondition);
		System.out.println(hotelList.size());
		
	}

}
