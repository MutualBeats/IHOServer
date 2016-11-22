package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ClientPO;
import util.ResultMessage;

public interface ClientDataService extends Remote{
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
	/*
	 * 注册企业会员
	 */
	public ResultMessage businessRegister (ClientPO po)throws RemoteException;
	/*
	 * 注册普通会员
	 */
	public ResultMessage originalRegister (ClientPO po)throws RemoteException ;
	/*
	 * 会员信息更新
	 */
	public ClientPO update (String name,ClientPO po)throws RemoteException;
	/*
	 * 会员等级更新
	 */
	public ResultMessage levelUpdate (ArrayList <Integer> levelList,int level) throws RemoteException;
	/*
	 * 会员信息查看
	 */
	public ClientPO query (String name,ClientPO po) throws RemoteException;
}
