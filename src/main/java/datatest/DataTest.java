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
				"huangxiao", 
				"江苏省 南通市 如皋市", 
				"如城商圈", 
				null, 
				-1, 
				-1, 
				"2018/12/12", "2018/12/12", 
				-1, Integer.MAX_VALUE, 
				RoomType.ALL, 
				false);

		ArrayList<HotelPO> hotelList = hotel.findHotelByCondition(searchCondition);
		System.out.println(hotelList.size());
		
	}

}
