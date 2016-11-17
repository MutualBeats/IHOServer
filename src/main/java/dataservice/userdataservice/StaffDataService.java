package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.StaffPO;

public interface StaffDataService extends Remote{
	/*
	 * 获取StaffPO内部数据
	 */
	public StaffPO findData(String StaffID) throws RemoteException;
}
