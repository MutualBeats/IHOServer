package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.MarketerPO;

public interface MarketerDataService {
	/*
	 * 获取MarketerPO内部数据
	 */
	public MarketerPO findData(String MarketerID) throws RemoteException;
}
