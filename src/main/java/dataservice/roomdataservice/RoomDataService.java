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
	/**
	 * 获取房间列表
	 * 
	 * @param hotelID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException;
	/**
	 * 添加酒店
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException;
	/**
	 * 入住
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Room checkIn(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 离开
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Room checkOut(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 获得预定记录
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<RoomRecordPO> getOrderRecord(String hotelID, String roomNumber) throws RemoteException;
	/**
	 * 增加房间预定记录
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException;
	/**
	 * 删除房间预定记录
	 * 
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException;
}
