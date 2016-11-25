/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.RoomPO;
import po.RoomRecordPO;

public interface RoomDataService extends Remote{
	
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException;
	
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException;
	
	public ResultMessage_Room checkIn(String hotelID, String roomNumber) throws RemoteException;
	
	public ResultMessage_Room checkOut(String hotelID, String roomNumber) throws RemoteException;
	
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException;
	
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException;
}
