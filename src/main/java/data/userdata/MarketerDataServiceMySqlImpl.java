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
import po.MarketerPO;
import util.ResultMessage;

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
	public ResultMessage updateData(MarketerPO po) throws RemoteException {
		if(po == null)
			return ResultMessage.UpdateFailed;
		sqlManager.getConnection();
		
		String sql = "UPDATE marketer SET marketer_name=?, contact_way=? WHERE marketer_id=? ";
		boolean updateSuccess;
		updateSuccess = sqlManager.executeUpdate(sql, new Object[]{po.getMarketername(), po.getTel_number(), po.getMarketerID()});
		
		return updateSuccess ? ResultMessage.UpdateSucceed : ResultMessage.UpdateFailed;
	}

	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM marketer WHERE marketer_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.get("password").equals(password))
			// TODO
			return null;
		else
			// TODO
			return null;
	}
	@Override
	public ResultMessage insert(MarketerPO po,String password) throws RemoteException {
		if(po == null)
			return ResultMessage.RegisterFail;
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		// TODO
		
		return ResultMessage.RegisterSuccess;
	}
	
	private MarketerPO getMarketerPO(Map<String, Object> map) {
		MarketerPO po = new MarketerPO();
		po.setMarketerID(map.get("marketer_id").toString());
		po.setMarketername(map.get("marketer_name").toString());
		po.setTel_number(map.get("contact_way").toString());
		return po;
	}

}
