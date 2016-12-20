/**
 * @author huangxiao
 * @version 2016年12月19日
 */
package data.hoteldata;

import java.rmi.RemoteException;

import util.hotel.SearchCondition;

public interface RoomInfo {
	
	public boolean checkRoomCondition(String hotelID, SearchCondition sc) throws RemoteException;

}
