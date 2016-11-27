/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.MarketerDataService;
import po.user.MarketerPO;
import util.ResultMessage_For_User;

public class MarketerDataServiceMySqlImpl extends UnicastRemoteObject implements MarketerDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	public MarketerDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public MarketerPO findData(String marketerID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM marketer WHERE marketer_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{marketerID});
		sqlManager.releaseAll();
		
		return getMarketerPO(map);
	}

	@Override
	public ResultMessage_For_User updateData(MarketerPO po) throws RemoteException {
		if(po == null)
			return null;
		sqlManager.getConnection();
		
		String sql = "UPDATE marketer SET marketer_name=?, contact_way=? WHERE marketer_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{po.getMarketername(), po.getTel_number(), po.getMarketerID()});
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.UpdateSuccess;
	}

	@Override
	public ResultMessage_For_User find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM marketer WHERE marketer_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_For_User.Account_Not_Exist;
		
		if(!map.get("password").toString().equals(password))
			return ResultMessage_For_User.PasswordWrong;
		
		return ResultMessage_For_User.LoginSuccess;
	}
	
	@Override
	public ResultMessage_For_User insert(MarketerPO po,String password) throws RemoteException {
		if(po == null)
			return null;
		
		if(findData(po.getMarketerID()) != null)
			return ResultMessage_For_User.Account_Exist;
		
		sqlManager.getConnection();
		
		String sql = "INSERT INTO marketer VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getMarketerID());
		params.add(password);
		params.add(po.getMarketername());
		params.add(po.getTel_number());
		
		sql = sqlManager.appendSQL(sql, params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.AddSucccess;
	}
	
	private MarketerPO getMarketerPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		MarketerPO po = new MarketerPO();
		po.setMarketerID(map.get("marketer_id").toString());
		po.setMarketername(map.get("marketer_name").toString());
		po.setTel_number(map.get("contact_way").toString());
		return po;
	}

}
