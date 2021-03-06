/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata.marketerdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.userdataservice.MarketerDataService;
import po.user.MarketerPO;
import util.resultmessage.ResultMessage_User;
import util.user.UserType;

public class MarketerDataServiceMySqlImpl extends UnicastRemoteObject implements MarketerDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	public MarketerDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public MarketerPO getMarketerInfo(String marketerID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM marketer WHERE marketer_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{marketerID});
		sqlManager.releaseAll();
		
		return getMarketerPO(map);
	}

	@Override
	public ResultMessage_User updateData(MarketerPO po) throws RemoteException {
		// 错误：marketerID不存在
		if(getMarketerInfo(po.getMarketerID()) == null)
			return ResultMessage_User.Account_Not_Exist;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE marketer SET marketer_name=?, contact_way=? WHERE marketer_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{po.getMarketerName(), po.getContactWay(), po.getMarketerID()});
		sqlManager.releaseConnection();
		
		return ResultMessage_User.UpdateSuccess;
	}
	
	@Override
	public ResultMessage_User insert(MarketerPO po,String password) throws RemoteException {
		// marketer已存在
		if(getMarketerInfo(po.getMarketerID()) != null)
			return ResultMessage_User.Account_Exist;
		sqlManager.getConnection();
		
		String sql;
		List<Object> params;

		// 添加marketer记录
		sql = "INSERT INTO marketer VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getMarketerID());
		params.add(po.getMarketerName());
		params.add(po.getContactWay());
		
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 添加user记录
		sql = "INSERT INTO user VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getMarketerID());
		params.add(UserType.MARKETER.toString().toLowerCase());
		params.add(password);
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		sqlManager.releaseConnection();
		
		return ResultMessage_User.Register_Success;
	}
	

	@Override
	public ArrayList<MarketerPO> getMarketerList() throws RemoteException {
		sqlManager.getConnection();
		String sql = "SELECT * FROM marketer";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{});
		sqlManager.releaseAll();
		
		ArrayList<MarketerPO> marketerList = new ArrayList<MarketerPO>();
		for (Map<String, Object> map : mapList) {
			marketerList.add(getMarketerPO(map));
		}
		return marketerList;
	}
	
	/**
	 * Map转换为MarketerPO
	 * @param map
	 * @return MarketerPO
	 */
	private MarketerPO getMarketerPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		MarketerPO po = new MarketerPO();
		po.setMarketerID(map.get("marketer_id").toString());
		po.setMarketerName(map.get("marketer_name").toString());
		po.setContactWay(map.get("contact_way").toString());
		return po;
	}

}
