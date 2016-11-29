package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.resultmessage.ResultMessage_User;

public interface ManagerDataService extends Remote{
	/*
	 * 检测是否允许登陆
	 */
	public ResultMessage_User find(String managerID, String password) throws RemoteException;
}
