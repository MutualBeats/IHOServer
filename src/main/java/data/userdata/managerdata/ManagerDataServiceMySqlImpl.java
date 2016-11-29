/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata.managerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.ManagerDataService;
import util.resultmessage.ResultMessage_User;

public class ManagerDataServiceMySqlImpl extends UnicastRemoteObject implements ManagerDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public ManagerDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	@Override
	public ResultMessage_User find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM manager WHERE manager_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_User.Account_Not_Exist;
		
		if(!map.get("password").toString().equals(password))
			return ResultMessage_User.PasswordWrong;
		
		return ResultMessage_User.LoginSuccess;
	}
	
}
