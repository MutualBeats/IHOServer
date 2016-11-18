package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.StaffPO;
import util.ResultMessage;

public interface StaffDataService extends Remote{
	/*
	 * 获取StaffPO内部数据
	 */
	public StaffPO findData(String StaffID) throws RemoteException;
	/*
	 * 在数据库中更新一个StaffPO记录
	 */
	public ResultMessage updateData(StaffPO po) throws RemoteException;
	/*
	 * 在数据库中生成一个staffPO记录
	 */
	public ResultMessage insert(StaffPO po) throws RemoteException;
}
