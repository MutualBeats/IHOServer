/**
 * @author huangxiao
 * @version 2016年11月27日
 */
package data.userdata.staffdata;

import java.rmi.RemoteException;

import po.hotel.HotelPO;

public interface HotelInfo {
	
	/**
	 * 获得酒店信息
	 * @param hotelID
	 * @return HotelPO
	 */
	public HotelPO getHotelInfo(String hotelID) throws RemoteException;

}
