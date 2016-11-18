package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.ResultMessage;

public interface ManagerDataService extends Remote{
	/*
	 * 检测是否允许登陆
	 */
	public ResultMessage find(String ID, String password) throws RemoteException;
}
