/**
 * @author huangxiao
 * @version 2016年12月5日
 */
package data.utildata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.databaseutility.SqlManager;
import dataservice.utildataservice.Identify;
import util.resultmessage.ResultMessage_Verify;

public class UtilDataServiceMySqlImpl extends UnicastRemoteObject implements Identify {

	private static final long serialVersionUID = 1L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	/**
	 * @throws RemoteException
	 */
	public UtilDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage_Verify login(String userID, String password) {
		sqlManager.getConnection();
		// TODO
		return null;
	}

	@Override
	public ResultMessage_Verify checkUserName(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
