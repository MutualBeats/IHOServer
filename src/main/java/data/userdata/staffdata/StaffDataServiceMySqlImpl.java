/**
 * @author huangxiao
 * 2016年11月17日
 */
package data.userdata.staffdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import data.datafactory.DataFactoryMySqlImpl;
import dataservice.userdataservice.StaffDataService;
import po.user.StaffPO;
import util.resultmessage.ResultMessage_User;
import util.user.UserType;

public class StaffDataServiceMySqlImpl extends UnicastRemoteObject implements StaffDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();
	
	private HotelInfo hotelInfo = DataFactoryMySqlImpl.getDataServiceInstance().getHotelInfo();
	
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
	public ResultMessage_User updateData(StaffPO po) throws RemoteException {
		// 错误：酒店工作人员不存在
		if(findData(po.getStaffID()) == null)
			return ResultMessage_User.Account_Not_Exist;
		
		sqlManager.getConnection();
		
		String sql = "UPDATE staff SET staff_name=? WHERE staff_id=? ";
		
		sqlManager.executeUpdate(sql, new Object[]{po.getStaffname(), po.getStaffID()});
		sqlManager.releaseConnection();
		
		return ResultMessage_User.UpdateSuccess;
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
	public ResultMessage_User insert(StaffPO po, String password) throws RemoteException {
		// 判断酒店是否存在
		if(hotelInfo.getHotelInfo(po.getHotelId()) == null)
			return ResultMessage_User.Hotel_Not_Exist;
		//一个酒店只有一个工作人员账号
		if(getStaffNum(po.getHotelId()) == 1)
			return ResultMessage_User.Hotel_Have_Staff;
		
		sqlManager.getConnection();
		
		String sql;
		List<Object> params;

		// 添加staff记录
		sql = "INSERT INTO staff VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getStaffID());
		params.add(po.getStaffname());
		params.add(po.getHotelId());
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 添加user记录
		sql = "INSERT INTO user VALUES ";
		params = new ArrayList<Object>();
		params.add(po.getStaffID());
		params.add(UserType.STAFF.toString().toLowerCase());
		params.add(password);
		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		sqlManager.releaseConnection();
		
		return ResultMessage_User.Register_Success;
	}
	
	@Override
	public ArrayList<StaffPO> getStaffList() throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM staff ORDER BY staff_id";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{});
		sqlManager.releaseAll();
		
		ArrayList<StaffPO> staffList = new ArrayList<StaffPO>();
		
		for (Map<String,Object> map : mapList) {
			staffList.add(getStaffPO(map));
		}
		
		return staffList;
	}
	
	/**
	 * Map转StaffPO
	 * @param map
	 * @return StaffPO
	 */
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
