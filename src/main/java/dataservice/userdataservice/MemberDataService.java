package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MemberPO;
import util.ResultMessage;

public interface MemberDataService extends Remote{
	/*
	 * 注册企业会员
	 */
	public ResultMessage businessRegister (MemberPO po)throws RemoteException;
	/*
	 * 注册普通会员
	 */
	public ResultMessage originalRegister (MemberPO po)throws RemoteException ;
	/*
	 * 会员信息更新
	 */
	public MemberPO update (String name,MemberPO po)throws RemoteException;
	/*
	 * 会员等级更新
	 */
	public ResultMessage levelUpdate (ArrayList <Integer> levelList,int level) throws RemoteException;
	/*
	 * 会员信息查看
	 */
	public MemberPO query (String name,MemberPO po) throws RemoteException;
}
