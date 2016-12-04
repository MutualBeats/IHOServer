/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata.clientdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.creditdata.ClientCreditUpdate;
import data.databaseutility.SqlManager;
import dataservice.userdataservice.ClientDataService;
import po.user.ClientPO;
import po.user.MemberPO;
import util.resultmessage.ResultMessage_User;
import util.user.MemberType;

public class ClientDataServiceMySqlImpl extends UnicastRemoteObject implements ClientDataService, ClientCreditUpdate {

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
	public ResultMessage_User updateData(String clientID, String clientName, String contactWay) throws RemoteException {
		if(clientID == null || clientName == null || contactWay == null)
			return ResultMessage_User.UpdateFail;
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET client_name=?, contact_way=? WHERE client_id=?";
		
		boolean result = sqlManager.executeUpdate(sql, new Object[]{clientName, contactWay, clientID});
		sqlManager.releaseConnection();
		
		if(!result)
			return ResultMessage_User.UpdateFail;
		
		return ResultMessage_User.UpdateSuccess;
	}

	@Override
	public ResultMessage_User find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM client WHERE client_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_User.Account_Not_Exist;
		
		if(!map.get("password").toString().equals(password))
			return ResultMessage_User.PasswordWrong;
		
		return ResultMessage_User.LoginSuccess;
	}

	@Override
	public ResultMessage_User insert(ClientPO po, String password) throws RemoteException {
		// ClientID 已存在
		if(findData(po.getClientID()) != null)
			return ResultMessage_User.Account_Exist;
		
		sqlManager.getConnection();
		
		String sql = "INSERT INTO client VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getClientID());
		params.add(password);
		params.add(po.getClientName());
		params.add(po.getContactWay());
		params.add(po.getCredit());
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		
		sql = sqlManager.appendSQL(sql, params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		// TODO 信用记录添加（动作：客户注册）
		
		// TODO ResultMessage统一
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
	public ResultMessage_User insertMember(MemberPO po) throws RemoteException {
		if(po == null)
			// TODO 注册何种会员判断
			return ResultMessage_User.BusinessRegisterFail;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET member_type=?, vip_level=?, member_info=? WHERE client_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		params.add(po.getClientID());
		
		boolean result = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		if(!result)
			// TODO 注册何种会员判断
			return ResultMessage_User.BusinessRegisterFail;
		return ResultMessage_User.BusinessRegisterSuccess;
	}

	@Override
	public ResultMessage_User updateMemberData(MemberPO po) throws RemoteException {
		if(po == null)
			return ResultMessage_User.UpdateFail;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET member_type=?, vip_level=?, member_info=? WHERE clinet_id=? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		params.add(po.getClientID());
		
		boolean result = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		if(!result)
			return ResultMessage_User.UpdateFail;
		
		return ResultMessage_User.UpdateSuccess;
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

	@Override
	public void creditUpdate(String clientID, int newCredit) {
		// TODO 异常错误处理
		
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET credit=? WHERE client_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{newCredit, clientID});
		sqlManager.releaseConnection();
		
	}

}
