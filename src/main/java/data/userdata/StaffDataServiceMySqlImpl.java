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
import dataservice.userdataservice.StaffDataService;
import po.StaffPO;
import util.ResultMessage;

public class StaffDataServiceMySqlImpl extends UnicastRemoteObject implements StaffDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	public StaffDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public StaffPO findData(String staffID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM staff WHERE staff_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{staffID});
		sqlManager.releaseAll();
		
		return getStaffPO(map);
	}

	@Override
	public ResultMessage updateData(StaffPO po) throws RemoteException {
		if(po == null)
			return ResultMessage.UpdateFailed;
		sqlManager.getConnection();
		
		String sql = "UPDATE staff SET staff_name=?, hotel_id=? WHERE staff_id=? ";
		boolean updateSuccess;
		updateSuccess = sqlManager.executeUpdate(sql, new Object[]{po.getStaffname(), po.getHotelId(), po.getStaffID()});
		
		return updateSuccess ? ResultMessage.UpdateSucceed : ResultMessage.UpdateFailed;
	}
	
	@Override
	public ResultMessage find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM staff WHERE staff_id=?";
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
	public ResultMessage insert(StaffPO po, String password) throws RemoteException {
		if(po == null)
			return ResultMessage.RegisterFail;
		sqlManager.getConnection();
		
		List<Object> params = new ArrayList<Object>();
		// TODO
		
		return ResultMessage.RegisterSuccess;
	}
	
	private StaffPO getStaffPO(Map<String, Object> map) {
		StaffPO po = new StaffPO();
		po.setStaffID(map.get("staff_id").toString());
		po.setHotelId(map.get("hotel_id").toString());
		po.setStaffname(map.get("staff_name").toString());
		return po;
	}

}
