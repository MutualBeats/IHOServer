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
	public ResultMessage_User regist(ClientPO po, String password) throws RemoteException {
		// ClientID 已存在
		if(queryClient(po.getClientID()) != null)
			return ResultMessage_User.Account_Exist;
		
		sqlManager.getConnection();
		
		String sql;
		// 添加cleint记录
		sql = "INSERT INTO client VALUES ";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getClientID());
		params.add(po.getClientName());
		params.add(po.getContactWay());
		params.add(po.getCredit());
		params.add(po.getMemberType().toString());
		params.add(po.getLevel());
		params.add(po.getMemberMessage());
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 添加user记录
		sql = "INSERT INTO user VALUES (?,?)";
		sqlManager.executeUpdate(sql, new Object[]{po.getClientID(), password});
		
		sqlManager.releaseConnection();
		
		// TODO 信用记录添加（动作：客户注册）
		
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
	public void creditUpdate(String clientID, int newCredit) {
		// TODO 异常错误处理
		sqlManager.getConnection();
		
		String sql = "UPDATE client SET credit=? WHERE client_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{newCredit, clientID});
		sqlManager.releaseConnection();
		
	}

}
