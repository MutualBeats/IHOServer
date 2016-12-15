/**
 * @author huangxiao
 * @version 2016年12月4日
 */
package dataservice.utildataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.resultmessage.ResultMessage_Verify;

public interface Identify extends Remote{

	public ResultMessage_Verify login(String user_name, String password) throws RemoteException;
	
	public ResultMessage_Verify checkUserName(String user_name) throws RemoteException;
	
	public ResultMessage_Verify checkIdentity(String user_name) throws RemoteException; 

}
