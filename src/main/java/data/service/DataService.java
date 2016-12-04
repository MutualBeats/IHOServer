/**
 * @author huangxiao
 * @version 2016年12月4日
 */
package data.service;

import java.rmi.RemoteException;

import data.creditdata.ClientCreditUpdate;
import data.roomdata.OrderUpdate;
import data.userdata.staffdata.HotelInfo;

public interface DataService {
	
	public HotelInfo getHotelInfo() throws RemoteException;
	
	public ClientCreditUpdate getClientCreditUpdate() throws RemoteException;
	
	public OrderUpdate getOrderUpdate() throws RemoteException;
	
}
