package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.user.ManagerPO;
import util.resultmessage.ResultMessage_User;

public interface ManagerDataService extends Remote{
	/**
	 * 获取网站管理人员信息
	 * @return ManagerPO
	 * @throws RemoteException
	 */
	public ManagerPO getManagerInfo() throws RemoteException;
	
	/**
	 * 更改网站管理人员信息
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_User changeManagerInfo(ManagerPO po) throws RemoteException;
	
}
