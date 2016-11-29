/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.room.RoomPO;
import po.room.RoomRecordPO;
import util.resultmessage.ResultMessage_Room;

public class RoomDataServiceImpl_Stub implements RoomDataService {

	@Override
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage_Room checkIn(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage_Room checkOut(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#getRoomInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public RoomPO getRoomInfo(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}
