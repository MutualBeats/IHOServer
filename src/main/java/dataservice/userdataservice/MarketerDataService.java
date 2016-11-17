package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.MarketerPO;

public interface MarketerDataService extends Remote{
	/*
	 * 获取MarketerPO内部数据
	 */
	public MarketerPO findData(String MarketerID) throws RemoteException;
}
