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
import dataservice.userdataservice.ClientDataService;
import po.ClientPO;
import po.MemberPO;
import util.MemberType;
import util.ResultMessage_For_User;

public class ClientDataServiceMySqlImpl extends UnicastRemoteObject implements ClientDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	public ClientDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public ClientPO findData(String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM client WHERE client_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{clientID});
		sqlManager.releaseAll();
		
		return getClientPO(map);
	}

	@Override
	public ResultMessage_For_User updateData(String clientID, String clientName, String contactWay) throws RemoteException {
		if(clientID == null || clientName == null || contactWay == null)
			return ResultMessage_For_User.UpdateFail;
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET client_name=?, contact_way=? WHERE client_id=?";
		
		sqlManager.executeUpdate(sql, new Object[]{clientName, contactWay, clientID});
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.UpdateSuccess;
	}

	@Override
	public ResultMessage_For_User find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM client WHERE client_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_For_User.Account_Not_Exist;
		
		if(!map.get("password").toString().equals(password))
			return ResultMessage_For_User.PasswordWrong;
		
		return ResultMessage_For_User.LoginSuccess;
	}

	@Override
	public ResultMessage_For_User insert(ClientPO po, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "INSERT INTO client VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		// TODO
		sql = sqlManager.appendSQL(sql, params.size());
		

		return null;
	}

	@Override
	public MemberPO findMemberData(String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT member_type, vip_level, member_info FROM client WHERE client_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{clientID});
		
		// clientID 不存在
		if(map.size() == 0)
			return null;
		
		return getMemberPO(clientID, map);
	}

	@Override
	public ResultMessage_For_User insertMember(MemberPO po) throws RemoteException {
		if(po == null)
			return null;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET member_type=?, vip_level=?, member_info=? WHERE client_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		params.add(po.getClientID());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return null;
	}

	@Override
	public ResultMessage_For_User updateMemberData(MemberPO po) throws RemoteException {
		if(po == null)
			return ResultMessage_For_User.UpdateFail;
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET member_type=?, vip_level=?, member_info=? WHERE clinet_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		params.add(po.getClientID());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.UpdateSuccess;
	}
	
	private ClientPO getClientPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		
		ClientPO po = new ClientPO();
		po.setClientID(map.get("client_id").toString());
		po.setClientName(map.get("client_name").toString());
		po.setContactWay(map.get("contact_way").toString());
		po.setCredit(Integer.parseInt(map.get("credit").toString()));
		po.setMemberType(MemberType.valueOf(map.get("member_type").toString()));
		po.setLevel(Integer.parseInt(map.get("vip_level").toString()));
		po.setMemberMessage(map.get("member_info").toString());
		
		return po;
	}
	
	private MemberPO getMemberPO(String clientID, Map<String, Object> map) {
		MemberPO po = new MemberPO();
		
		po.setClientID(clientID);
		po.setMemberType(MemberType.valueOf(map.get("member_type").toString()));
		po.setLevel(Integer.parseInt(map.get("vip_level").toString()));
		po.setMemberMessage(map.get("member_info").toString());
		
		return po;
	}

}
