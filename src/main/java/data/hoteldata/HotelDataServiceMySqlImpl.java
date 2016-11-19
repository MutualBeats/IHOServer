/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.hoteldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.hoteldataservice.HotelDataService;
import po.HotelEvaluationPO;
import po.HotelPO;
import util.SearchCondition;

public class HotelDataServiceMySqlImpl extends UnicastRemoteObject implements HotelDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public HotelDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public HotelPO findHotelData(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM hotel WHERE hotelID=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{hotelID});
		sqlManager.releaseAll();
		
		return getHotelPO(map);
	}

	@Override
	public ArrayList<HotelPO> findHotelListData(SearchCondition sc) throws RemoteException {
		if(sc == null || sc.region == null || sc.businessDistrict == null)
			return null;
		sqlManager.getConnection();
		
		ArrayList<HotelPO> hotelList = new ArrayList<HotelPO>();
		String sql = "SELECT * FROM hotel WHERE region=? AND business_district=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(sc.region);
		params.add(sc.businessDistrict);
		if(sc.address != null) {
			sql += " AND address=? ";
			params.add(sc.address);
		}
		if(sc.hotelName != null) {
			sql += " AND hotel_name=? ";
			params.add(sc.hotelName);
		}
		if(sc.starLevel >= 0) {
			sql += " AND star_level=? ";
			params.add(sc.starLevel);
		}
		if(sc.score >= 0) {
			sql += " AND score>=? ";
			params.add(sc.score);
		}
		sql += " ORDER BY score DESC";
		
		ArrayList<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql, params);
		for(Map<String, Object> map : mapList) {
			hotelList.add(getHotelPO(map));
		}
		sqlManager.releaseAll();
		
		return hotelList;
	}

	@Override
	public void updateHotelData(HotelPO po) throws RemoteException {
		if(po == null)
			return;
		sqlManager.getConnection();
		
		String sql = "UPDATE hotel SET hotel_name=?, address=?, region=?, business_district=? WHERE hotel_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelName());
		params.add(po.getAddress());
		params.add(po.getRegion());
		params.add(po.getBusinessDistrict());
		params.add(po.getHotelID());
		sqlManager.executeUpdateByList(sql, params);
		
		sqlManager.releaseConnection();
	}

	@Override
	public ArrayList<HotelEvaluationPO> findHotelEvaluation(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<HotelEvaluationPO> evaluationList = new ArrayList<HotelEvaluationPO>();
		String sql = "SELECT * FROM hotel_evaluation WHERE hotel_id=?";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID});
		for(Map<String, Object> map : mapList) {
			evaluationList.add(getHotelEvaluationPO(map));
		}
		sqlManager.releaseAll();
		
		return evaluationList;
	}

	@Override
	public void insertHotelEvaluation(HotelEvaluationPO po) throws RemoteException {
		if(po == null)
			return;
		sqlManager.getConnection();
		
		String sql = sqlManager.appendSQL("INSERT INTO hotel_evaluation VALUES ", 5);
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getClientName());
		params.add(po.getEvaluateTime());
		params.add(po.getEvaluateScore());
		params.add(po.getEvaluateInfo());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
	}
	
	@Override
	public void insertHotel(HotelPO po) throws RemoteException {
		if(po == null)
			return;
		sqlManager.getConnection();
		
		String sql = sqlManager.appendSQL("INSERT INTO hotel VALUES ", 7);
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getHotelName());
		params.add(po.getAddress());
		params.add(po.getRegion());
		params.add(po.getBusinessDistrict());
		params.add(po.getStarLevel());
		params.add(po.getScore());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
	}
	
	private HotelPO getHotelPO(Map<String, Object> map) {
		HotelPO po = new HotelPO();
		
		po.setHotelID(map.get("hotel_id").toString());
		po.setHotelName(map.get("hotel_name").toString());
		po.setAddress(map.get("address").toString());
		po.setRegion(map.get("region").toString());
		po.setBusinessDistrict(map.get("business_district").toString());
		po.setStarLevel(Integer.parseInt(map.get("star_level").toString()));
		po.setScore(Double.parseDouble(map.get("score").toString()));
		
		return po;
	}
	
	private HotelEvaluationPO getHotelEvaluationPO(Map<String, Object> map) {
		HotelEvaluationPO po = new HotelEvaluationPO();
		
		po.setHotelID(map.get("hotel_id").toString());
		po.setClientName(map.get("client_id").toString());
		po.setEvaluateTime(map.get("time").toString());
		po.setEvaluateScore(Integer.parseInt(map.get("score").toString()));
		po.setEvaluateInfo(map.get("evaluate_info").toString());
		
		return po;
	}

}
