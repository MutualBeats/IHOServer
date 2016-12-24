/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.room.RoomPO;
import po.room.RoomRecordPO;
import util.resultmessage.ResultMessage_Room;

public interface RoomDataService extends Remote {
	
	/**
	 * 获取房间列表
	 * 
	 * @param hotelID
	 * @return ArrayList<RoomPO>
	 * @throws RemoteException
	 */
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException;
	/**
	 * 获取房间信息
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return RoomPO
	 * @throws RemoteException
	 */
	public RoomPO getRoomInfo(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 录入客房
	 * 
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException;
	/**
	 * 入住
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Room checkIn(String hotelID, String roomNumber, boolean isOnline) throws RemoteException;
	/**
	 * 离开
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Room checkOut(String hotelID, String roomNumber, boolean isOnline) throws RemoteException;
	/**
	 * 获得房间未来预订记录
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return ArrayList<RoomRecordPO> 
	 * @throws RemoteException
	 */
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 增加房间预订记录
	 * 
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException;
	/**
	 * 删除房间预订记录
	 * 
	 * @param orderID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException;

	
}
