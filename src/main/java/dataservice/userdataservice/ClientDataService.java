package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.user.ClientPO;
import po.user.MemberPO;
import util.resultmessage.ResultMessage_User;

public interface ClientDataService extends Remote{
	/*
	 * 获取ClientPO内部数据
	 */
	public ClientPO findData(String clientID) throws RemoteException;
	/*
	 * 在数据库中更新一个ClientPO记录
	 */
	public ResultMessage_User updateData(String clientID, String clientName, String contactWay) throws RemoteException;
	/*
	 * 检测是否允许登陆
	 */
	public ResultMessage_User find(String clientID, String password) throws RemoteException;
	/*
	 * 在数据库中生成一个clientPO记录
	 */
	public ResultMessage_User insert(ClientPO po, String password) throws RemoteException;
	/*
	 * 获取MemberPO内部数据
	 */
	public MemberPO findMemberData (String clientID) throws RemoteException;
	/*
	 * 在数据库生成一个MemberPO记录
	 */
	public ResultMessage_User insertMember (MemberPO po)throws RemoteException;
	/*
	 *在数据库更新一个MemberPO记录
	 */
	public ResultMessage_User updateMemberData (MemberPO po)throws RemoteException;
}
