package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.StaffChangePO;
import po.user.StaffPO;
import util.resultmessage.ResultMessage_User;

public interface StaffDataService extends Remote{

	public StaffPO findData(String staffID) throws RemoteException;
	
	public ResultMessage_User updateData(StaffChangePO po) throws RemoteException;
	
	public ResultMessage_User insert(StaffPO po, String password) throws RemoteException;
	
	public ArrayList<StaffPO> getStaffList() throws RemoteException;
	
}
