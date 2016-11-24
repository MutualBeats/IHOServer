package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ClientPO;
import po.MemberPO;
import util.ResultMessage;
import util.ResultMessage_For_User;

public interface ClientDataService extends Remote{
	/*
	 * 获取ClientPO内部数据
	 */
	public ClientPO findData(String clientID) throws RemoteException;
	/*
	 * 在数据库中更新一个ClientPO记录
	 */
	public ResultMessage_For_User updateData(String clientID, String clientName, String contactWay) throws RemoteException;
	/*
	 * 检测是否允许登陆
	 */
	public ResultMessage_For_User find(String clientID, String password) throws RemoteException;
	/*
	 * 在数据库中生成一个clientPO记录
	 */
	public ResultMessage_For_User insert(ClientPO po, String password) throws RemoteException;
	/*
	 * 获取MemberPO内部数据
	 */
	public MemberPO findMemberData (String clientID) throws RemoteException;
	/*
	 * 在数据库生成一个MemberPO记录
	 */
	public ResultMessage_For_User insertMember (MemberPO po)throws RemoteException;
	/*
	 *在数据库更新一个MemberPO记录
	 */
	public ResultMessage_For_User updateMemberData (MemberPO po)throws RemoteException;
}
