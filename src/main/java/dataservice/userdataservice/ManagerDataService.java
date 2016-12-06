package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.user.ManagerPO;
import util.resultmessage.ResultMessage_User;

public interface ManagerDataService extends Remote{

	public ManagerPO getManagerInfo() throws RemoteException;
	
	public ResultMessage_User changeManagerInfo(ManagerPO po) throws RemoteException;
	
}
