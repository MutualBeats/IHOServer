/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.creditdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.creditdataservice.CreditDataService;
import dataservice.creditdataservice.ResultMessage_Credit;
import po.credit.CreditPO;
import util.CreditChangeAction;

public class CreditDataServiceMySqlImpl extends UnicastRemoteObject implements CreditDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public CreditDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage_Credit insert(CreditPO po) throws RemoteException {
		if(po == null)
			return ResultMessage_Credit.Update_Failed;
		// TODO 用户不存在
		
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getClientID());
		params.add(po.getChangeTime());
		params.add(po.getAction().toString());
		params.add(po.getOrderID());
		params.add(po.getChangeValue());
		params.add(po.getCredit());
		
		String sql = sqlManager.appendSQL("INSERT INTO credit VALUES ", params.size());

		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_Credit.Update_Successful;
	}
	
	@Override
	public ArrayList<CreditPO> find(String clientID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<CreditPO> creditList = new ArrayList<CreditPO>();
		String sql = "SELECT * FROM credit WHERE client_id=? ORDER BY time DESC";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{clientID});
		for(Map<String, Object> map: mapList) {
			creditList.add(getCreditPO(map));
		}
		sqlManager.releaseAll();
		
		return creditList;
	}
	
	private CreditPO getCreditPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		CreditPO po = new CreditPO();
		po.setClientID(map.get("client_id").toString());
		po.setChangeTime(map.get("time").toString());
		po.setAction(CreditChangeAction.valueOf(map.get("action").toString()));
		po.setOrderID(map.get("order_id").toString());
		po.setChangeValue(Integer.parseInt(map.get("value").toString()));
		po.setCredit(Integer.parseInt(map.get("credit").toString()));
		return po;
	}

}
