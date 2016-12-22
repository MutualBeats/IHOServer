package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.MarketerPO;
import util.resultmessage.ResultMessage_User;

public interface MarketerDataService extends Remote{
	/**
	 * 获取网站营销人员信息
	 * @param marketerID
	 * @return MarketerPO
	 * @throws RemoteException
	 */
	public MarketerPO getMarketerInfo(String marketerID) throws RemoteException;
	/**
	 * 更新网站营销人员信息
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_User updateData(MarketerPO po) throws RemoteException;
	/**
	 * 添加网站营销人员
	 * @param po
	 * @param password
	 * @return ReusultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_User insert (MarketerPO po, String password) throws RemoteException;
	/**
	 * 获取所有网站营销人员列表
	 * @return ArrayList<MarketerPO>
	 * @throws RemoteException
	 */
	public ArrayList<MarketerPO> getMarketerList() throws RemoteException;
}
