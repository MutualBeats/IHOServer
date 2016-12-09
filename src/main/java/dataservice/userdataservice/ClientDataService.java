package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.ClientInfoChangePO;
import po.user.ClientPO;
import po.user.ClientRegistPO;
import po.user.MemberPO;
import util.resultmessage.ResultMessage_User;

public interface ClientDataService extends Remote{
	
	public ResultMessage_User regist(ClientRegistPO po) throws RemoteException;
	
	/**
	 * 查询用户
	 * 
	 * @param clientID
	 * @return
	 * @throws RemoteException
	 */
	public ClientPO queryClient(String clientID) throws RemoteException;

	/**
	 * 更新用户信息
	 * 
	 * @param changePO
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_User updateClientInfo(ClientInfoChangePO changePO) throws RemoteException;

	/**
	 * Register Member
	 * 
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_User registerMember(MemberPO po) throws RemoteException;
	
	/**
	 * Get all the client info 
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<ClientPO> getClientList() throws RemoteException;
}
