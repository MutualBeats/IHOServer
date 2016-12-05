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
import data.datafactory.DataFactoryMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import po.credit.CreditPO;
import util.credit.CreditChangeAction;
import util.resultmessage.ResultMessage_Credit;
import util.resultmessage.ResultMessage_User;

public class CreditDataServiceMySqlImpl extends UnicastRemoteObject implements CreditDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	private ClientCreditUpdate client = DataFactoryMySqlImpl.getDataServiceInstance().getClientCreditUpdate();

	public CreditDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage_Credit insert(CreditPO po) throws RemoteException {
		// 更新信用
		ResultMessage_User res = client.creditUpdate(po.getClientID(), po.getCredit());
		// 客户不存在
		if(res.equals(ResultMessage_User.Account_Not_Exist))
			return ResultMessage_Credit.Update_Failed;
		
		// 添加信用记录
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
