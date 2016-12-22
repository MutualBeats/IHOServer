/**
 * @author huangxiao
 * @version 2016年12月4日
 */
package data.service;

import java.rmi.RemoteException;

import data.creditdata.ClientCreditUpdate;
import data.hoteldata.RoomInfo;
import data.promotiondata.MemberLevelUpdate;
import data.userdata.clientdata.GetMemberLevel;
import rmihelper.ClientInfo;
import rmihelper.CreditUpdate;
import rmihelper.OrderUpdate;
import rmihelper.RoomUpdate;

public interface DataService {
		
	public ClientCreditUpdate getClientCreditUpdate() throws RemoteException;
	
	public OrderUpdate getOrderUpdate() throws RemoteException;
	
	public CreditUpdate getCreditUpdate() throws RemoteException;
	
	public ClientInfo getClientInfo() throws RemoteException;
	
	public MemberLevelUpdate getMemberLevelUpdate() throws RemoteException;
	
	public RoomUpdate getRoomUpdate() throws RemoteException;
	
	public RoomInfo getRoomInfo() throws RemoteException;
	
	public GetMemberLevel getMemberLevel() throws RemoteException; 
	
}
