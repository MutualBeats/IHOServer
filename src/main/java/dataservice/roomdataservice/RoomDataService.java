/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.RoomPO;

public interface RoomDataService extends Remote{
	public ArrayList<RoomPO> find(String HotelID) throws RemoteException;
	
	public void insert(RoomPO po) throws RemoteException;
	
	public void update(RoomPO po) throws RemoteException;
}
