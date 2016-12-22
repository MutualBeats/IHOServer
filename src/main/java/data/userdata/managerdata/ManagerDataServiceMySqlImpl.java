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
import po.user.ManagerPO;
import util.resultmessage.ResultMessage_User;

public class ManagerDataServiceMySqlImpl extends UnicastRemoteObject implements ManagerDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public ManagerDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public ManagerPO getManagerInfo() throws RemoteException {
		sqlManager.getConnection();
		String sql = "SELECT * FROM manager";
		Map<String, Object> map = sqlManager.querySimple(sql, null);
		sqlManager.releaseAll();
		return getManagerPO(map);
	}

	@Override
	public ResultMessage_User changeManagerInfo(ManagerPO po) throws RemoteException {
		sqlManager.getConnection();
		String sql = "UPDATE manager SET manager_name=?, contact_way=? WHERE manager_id=? ";
		boolean res = sqlManager.executeUpdate(sql, new Object[]{po.getManagerName(), po.getContactWay(), po.getManagerID()});
		sqlManager.releaseConnection();
		if(!res)
			return ResultMessage_User.Account_Not_Exist;
		return ResultMessage_User.UpdateSuccess;
	}

	private ManagerPO getManagerPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		ManagerPO po = new ManagerPO();
		po.setManagerID(map.get("manager_id").toString());
		po.setManagerName(map.get("manager_name").toString());
		po.setContactWay(map.get("contact_way").toString());
		return po;
	}
	
	
}
