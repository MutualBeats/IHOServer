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
import util.ResultMessage;

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

	/* (non-Javadoc)
	 * @see dataservice.userdataservice.ClientDataService#updateData(po.ClientPO)
	 */
	@Override
	public ResultMessage updateData(ClientPO po) throws RemoteException {
		if(po == null)
			return ResultMessage.UpdateFailed;
		sqlManager.getConnection();
		// TODO
		String mysql;
		
		return null;
		
	}

	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM client WHERE client_id=?";
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
	public ResultMessage insert(ClientPO po, String password) throws RemoteException {
		if(po == null)
			return ResultMessage.RegisterFail;
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		// TODO
		
		return ResultMessage.RegisterSuccess;
	}
	
	private ClientPO getClientPO(Map<String, Object> map) {
		ClientPO po = new ClientPO();
		po.setClientID(map.get("client_id").toString());
		po.setClientname(map.get("client_name").toString());
		po.setTel_number(map.get("contact_way").toString());
		// TODO
		return po;
	}

}
