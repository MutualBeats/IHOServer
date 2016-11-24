/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.ManagerDataService;
import util.ResultMessage;

public class ManagerDataServiceMySqlImpl extends UnicastRemoteObject implements ManagerDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public ManagerDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM manager WHERE manager_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0) {
			// TODO manager_id不存在
			return null;
		}
		
		if(map.get("password").toString().equals(password))
			// TODO 登录成功信息
			return null;
		else
			// TODO 登录失败信息
			return null;
	}
	
}
