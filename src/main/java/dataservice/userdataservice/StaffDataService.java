package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.user.StaffChangePO;
import po.user.StaffPO;
import util.resultmessage.ResultMessage_User;

public interface StaffDataService extends Remote{
	/**
	 * 获取酒店工作人员信息
	 * @param staffID
	 * @return StaffPO
	 * @throws RemoteException
	 */
	public StaffPO findData(String staffID) throws RemoteException;
	/**
	 * 更新酒店工作人员信息
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_User updateData(StaffChangePO po) throws RemoteException;
	/**
	 * 添加酒店工作人员
	 * @param po
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage_User insert(StaffPO po, String password) throws RemoteException;
	/**
	 * 获取所有酒店工作人员列表
	 * @return ArrayList<StaffPO>
	 * @throws RemoteException
	 */
	public ArrayList<StaffPO> getStaffList() throws RemoteException;
	
}
