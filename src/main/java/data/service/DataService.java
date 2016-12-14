/**
 * @author huangxiao
 * @version 2016年12月4日
 */
package data.service;

import java.rmi.RemoteException;

import data.creditdata.ClientCreditUpdate;
import rmihelper.ClientInfo;
import rmihelper.CreditUpdate;
import rmihelper.OrderUpdate;
import rmihelper.RoomUpdate;

public interface DataService {
		
	public ClientCreditUpdate getClientCreditUpdate() throws RemoteException;
	
	public OrderUpdate getOrderUpdate() throws RemoteException;
	
	public CreditUpdate getCreditUpdate() throws RemoteException;
	
	public ClientInfo getClientInfo() throws RemoteException;
	
	public RoomUpdate getRoomUpdate() throws RemoteException;
		
}
