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
import po.user.StaffPO;
import util.ResultMessage_For_User;

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
	public ResultMessage_For_User updateData(StaffPO po) throws RemoteException {
		if(po == null)
			return null;
		sqlManager.getConnection();
		
		String sql = "UPDATE staff SET staff_name=? WHERE staff_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{po.getStaffname(), po.getStaffID()});
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.UpdateSuccess;
	}
	
	@Override
	public ResultMessage_For_User find(String ID, String password) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT password FROM staff WHERE staff_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{ID});
		sqlManager.releaseAll();
		
		if(map.size() == 0)
			return ResultMessage_For_User.Account_Not_Exist;
		
		if(!map.get("password").toString().equals(password))
			return ResultMessage_For_User.PasswordWrong;
		
		return ResultMessage_For_User.LoginSuccess;
	}
	
	/**
	 * 获得某酒店的工作人员账号个数
	 * @param hotelID
	 * @return result
	 */
	private int getStaffNum(String hotelID) {
		sqlManager.getConnection();
		String sql = "SELECT * FROM staff WHERE hotel_id=? ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID});
		sqlManager.releaseAll();
		return mapList.size();
	}
	
	@Override
	public ResultMessage_For_User insert(StaffPO po, String password) throws RemoteException {
		if(po == null)
			return null;
		// TODO 判断酒店是否存在
		
		//一个酒店只有一个工作人员账号
		if(getStaffNum(po.getHotelId()) == 1)
			return ResultMessage_For_User.Hotel_Have_Staff;
		
		sqlManager.getConnection();
		
		String sql = "INSERT INTO staff VALUES ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getStaffID());
		params.add(password);
		params.add(po.getStaffname());
		params.add(po.getHotelId());
		
		sql = sqlManager.appendSQL(sql, params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		
		return ResultMessage_For_User.AddSucccess;
	}
	
	private StaffPO getStaffPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		StaffPO po = new StaffPO();
		po.setStaffID(map.get("staff_id").toString());
		po.setHotelId(map.get("hotel_id").toString());
		po.setStaffname(map.get("staff_name").toString());
		return po;
	}

}
