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
import po.user.ClientInfoChangePO;
import po.user.ClientPO;
import po.user.ClientRegistPO;
import po.user.MemberPO;
import rmihelper.ClientInfo;
import util.resultmessage.ResultMessage_User;
import util.user.MemberType;
import util.user.UserType;

public class ClientDataServiceMySqlImpl extends UnicastRemoteObject implements ClientDataService, ClientCreditUpdate, ClientInfo {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
		
	public ClientDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	@Override
	public ResultMessage_User regist(ClientRegistPO po) throws RemoteException {
		// ClientID 已存在
		if(queryClient(po.getId()) != null)
			return ResultMessage_User.Account_Exist;
		
		sqlManager.getConnection();
		
		String sql;
		List<Object> params;

		// 添加cleint记录
		sql = "INSERT INTO client VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getId());
		params.add(po.getName());
		params.add(po.getContactWay());
		params.add(0);
		params.add(MemberType.Not.toString());
		params.add(0);
		params.add("");
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 添加user记录
		sql = "INSERT INTO user VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getId());
		params.add(UserType.CLIENT.toString().toLowerCase());
		params.add(po.getPsw());
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		sqlManager.releaseConnection();
		
		return ResultMessage_User.Register_Success;
	}

	@Override
	public ClientPO queryClient(String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM client WHERE client_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{clientID});
		sqlManager.releaseAll();
		
		return getClientPO(map);
	}

	@Override
	public ResultMessage_User updateClientInfo(ClientInfoChangePO changePO) throws RemoteException {
		// 错误：客户不存在
		if(queryClient(changePO.getClientID()) == null)
			return ResultMessage_User.Account_Not_Exist;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET client_name=?, contact_way=? WHERE client_id=?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(changePO.getClientName());
		params.add(changePO.getContactWay());
		params.add(changePO.getClientID());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_User.UpdateSuccess;
	}

	@Override
	public ResultMessage_User registerMember(MemberPO po) throws RemoteException {
		if(po == null)
			return ResultMessage_User.Regitster_Failed;
		
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
			return ResultMessage_User.Regitster_Failed;
		return ResultMessage_User.Register_Success;
	}
	
	
	@Override
	public ArrayList<ClientPO> getClientList() throws RemoteException {
		sqlManager.getConnection();
		String sql = "SELECT * FROM client ORDER BY client_id";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{});
		sqlManager.releaseAll();
		
		ArrayList<ClientPO> clientList = new ArrayList<ClientPO>();
		for (Map<String, Object> map : mapList) {
			clientList.add(getClientPO(map));
		}
		
		return clientList;
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
	
//	private MemberPO getMemberPO(String clientID, Map<String, Object> map) {
//		MemberPO po = new MemberPO();
//		
//		po.setClientID(clientID);
//		po.setMemberType(MemberType.valueOf(map.get("member_type").toString()));
//		po.setLevel(Integer.parseInt(map.get("vip_level").toString()));
//		po.setMemberMessage(map.get("member_info").toString());
//		
//		return po;
//	}

	@Override
	public ResultMessage_User creditUpdate(String clientID, int newCredit) {
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET credit=? WHERE client_id=? ";
		
		boolean res = sqlManager.executeUpdate(sql, new Object[]{newCredit, clientID});
		sqlManager.releaseConnection();
		
		if(!res)
			return ResultMessage_User.Account_Not_Exist;
		
		return ResultMessage_User.UpdateSuccess;
	}

	@Override
	public ClientPO getClientInfo(String clientID) {
		ClientPO clientPO = null;
		try {
			clientPO = queryClient(clientID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return clientPO;
	}

}
