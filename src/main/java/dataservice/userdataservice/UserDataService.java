package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.user.ClientPO;
import po.user.MarketerPO;
import po.user.StaffPO;

public interface UserDataService {
	/*
	 * 获取ClientPO内部数据
	 */
	public ClientPO findClientData(String ClientID) throws RemoteException;
	/*
	 * 获取StaffPO内部数据
	 */
	public StaffPO findStaffData(String StaffID) throws RemoteException;
	/*
	 * 获取MarketerPO内部数据
	 */
	public MarketerPO findMarketerData(String MarketerID) throws RemoteException;
	/*t
	 * 在数据库中更新一个ClientPO记录
	 */
	public void updateClientData(ClientPO po) throws RemoteException;
	/*
	 * 在数据库中更新一个StaffPO记录
	 */
	public void updateStaffData(StaffPO po) throws RemoteException;
	/*
	 * 在数据库中更新一个MarketerPO记录
	 */
	public void updateMarketerData(MarketerPO po) throws RemoteException;
	/*
	 * 在数据库中生成一个marketerPO记录
	 */
	public void insertMarketer (MarketerPO po) throws RemoteException;
	/*
	 * 在数据库中生成一个staffPO记录
	 */
	public void insertStaff (StaffPO po) throws RemoteException;
	/*
	 * 检测是否允许登陆
	 */
	public boolean findClient(String ID, String password) throws RemoteException;
	/*
	 * 在数据库中生成一个clientPO记录
	 */
	public void insertClient(ClientPO po, String password) throws RemoteException;
}
