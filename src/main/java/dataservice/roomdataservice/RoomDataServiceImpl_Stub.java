/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.RoomPO;
import util.RoomCheckRecord;
import util.RoomConditionOfDate;
import util.RoomType;

public class RoomDataServiceImpl_Stub implements RoomDataService {

	@Override
	public ArrayList<RoomPO> find(String HotelID) throws RemoteException {
		ArrayList<RoomPO> roomList = new ArrayList<RoomPO>();
		roomList.add(new RoomPO("00000001", "1024", RoomType.Single, 100, new ArrayList<RoomCheckRecord>(), new ArrayList<RoomConditionOfDate>()));
		return roomList;
	}

	@Override
	public void insert(RoomPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public void update(RoomPO po) throws RemoteException {
		System.out.println("Update Succeed!\n");
	}

}
