package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.ClientPO;
import util.ResultMessage;

public interface ClientDataService {
	/*
	 * 获取ClientPO内部数据
	 */
	public ClientPO findData(String ClientID) throws RemoteException;
	/*
	 * 在数据库中更新一个ClientPO记录
	 */
	public ResultMessage updateData(ClientPO po) throws RemoteException;
	/*
	 * 检测是否允许登陆
	 */
	public ResultMessage find(String ID, String password) throws RemoteException;
	/*
	 * 在数据库中生成一个clientPO记录
	 */
	public ResultMessage insert(ClientPO po, String password) throws RemoteException;
}
