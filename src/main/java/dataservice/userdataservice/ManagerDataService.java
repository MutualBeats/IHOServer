package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.ClientPO;
import po.MarketerPO;
import po.StaffPO;
import util.ResultMessage;

public interface ManagerDataService {
	/*
	 * 获取ClientPO内部数据
	 */
	public ClientPO findClientData(String ClientID) throws RemoteException;
	/*
	 * 获取StaffPO内部数据
	 */
	public StaffPO findStaffData(String StaffID) throws RemoteException;
	/*
	 * 在数据库中更新一个StaffPO记录
	 */
	public ResultMessage updateStaffData(StaffPO po) throws RemoteException;
	/*
	 * 在数据库中生成一个staffPO记录
	 */
	public ResultMessage insertStaff (StaffPO po) throws RemoteException;
	/*
	 * 获取MarketerPO内部数据
	 */
	public MarketerPO findMarketerData(String MarketerID) throws RemoteException;
	/*
	 * 在数据库中更新一个MarketerPO记录
	 */
	public ResultMessage updateMarketerData(MarketerPO po) throws RemoteException;
	/*
	 * 在数据库中生成一个marketerPO记录
	 */
	public ResultMessage insertMarketer (MarketerPO po) throws RemoteException;
}
