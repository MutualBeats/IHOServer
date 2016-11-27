/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.roomdata.RoomDataServiceMySqlImpl;
import data.userdata.StaffDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.StaffDataService;
import po.room.RoomPO;
import util.RoomCondition;
import util.RoomType;

public class DataTest {
	
	public CreditDataService credit;
	public HotelDataService hotel;
	public StaffDataService staff;
	public RoomDataService room;
	
	public DataTest() throws Exception {
		credit = new CreditDataServiceMySqlImpl();
		hotel = new HotelDataServiceMySqlImpl();
		staff = new StaffDataServiceMySqlImpl();
		room = new RoomDataServiceMySqlImpl();
	}
	
	public static void main(String[] args) throws Exception {
		DataTest test = new DataTest();
		// addRoom 测试
//		RoomPO po = new RoomPO();
//		po.setHotelID("00000001");
//		po.setRoomNumber("3B322");
//		po.setType(RoomType.Triple);
//		po.setPrice(512);
//		po.setCondition(RoomCondition.NotReserved);
//		
//		System.out.println(test.room.addRoom(po));
		
		// checkIn 测试
//		System.out.println(test.room.checkIn("00000001", "3B323"));
		
		// checkOut 测试
//		System.out.println(test.room.checkOut("00000001", "3B323"));
		
//		ArrayList<RoomPO> list = test.room.getRoom("00000001");
//		for (RoomPO roomPO : list) {
//			System.out.println(roomPO.getRoomNumber() + "\t" + roomPO.getType() + "\t" + roomPO.getPrice());
//		}
		
		System.out.println(test.room.checkOut("00000001", "3B323"));
				
	}

}
