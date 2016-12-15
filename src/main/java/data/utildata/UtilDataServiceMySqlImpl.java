/**
 * @author huangxiao
 * @version 2016年12月5日
 */
package data.utildata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

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
	public ResultMessage_Verify login(String user_name, String password) {
		sqlManager.getConnection();
		
		String sql = "SELECT password, identity FROM user WHERE id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{user_name});
		sqlManager.releaseAll();
		
		// 错误：用户id不存在
		if(map.size() == 0)
			return ResultMessage_Verify.USER_NOT_EXIST;
		// 错误：密码错误
		if(!map.get("password").toString().equals(password))
			return ResultMessage_Verify.PASSWORD_WRONG;
		
		String userType = map.get("identity").toString().toUpperCase();
		return ResultMessage_Verify.valueOf(userType);
	}

	@Override
	public ResultMessage_Verify checkUserName(String user_name) {
		sqlManager.getConnection();
		
		String sql = "SELECT id FROM user WHERE id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{user_name});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_Verify.USER_NOT_EXIST;
		
		return ResultMessage_Verify.USER_EXIST_ALREADY;
	}

	@Override
	public ResultMessage_Verify checkIdentity(String user_name) {
		sqlManager.getConnection();
		
		String sql = "SELECT identity FROM user WHERE id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{user_name});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_Verify.USER_NOT_EXIST;
		
		String userType = map.get("identity").toString().toUpperCase();
		return ResultMessage_Verify.valueOf(userType);
	}
	

}
