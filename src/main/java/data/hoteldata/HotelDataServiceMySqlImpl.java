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
import dataservice.hoteldataservice.ResultMessage_Hotel;
import po.hotel.HotelEvaluationPO;
import po.hotel.HotelPO;
import util.SearchCondition;

public class HotelDataServiceMySqlImpl extends UnicastRemoteObject implements HotelDataService {

	private static final long serialVersionUID = 2L;

	private SqlManager sqlManager = SqlManager.getInstance();

	public HotelDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	@Override
	public HotelPO getHotelInfo(String hotelID) throws RemoteException {
		sqlManager.getConnection();

		String sql = "SELECT * FROM hotel WHERE hotel_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[] { hotelID });
		sqlManager.releaseAll();

		return getHotelPO(map);
	}

	// TODO 根据房间信息搜索
	@Override
	public ArrayList<HotelPO> findHotelByCondition(SearchCondition sc) throws RemoteException {
		// 必须明确地址和商圈才能进行搜索
		if (sc == null || sc.region == null || sc.businessDistrict == null)
			return null;
		sqlManager.getConnection();

		ArrayList<HotelPO> hotelList = new ArrayList<HotelPO>();
		String sql = "SELECT * FROM hotel WHERE region=? AND business_district=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(sc.region);
		params.add(sc.businessDistrict);
		if (sc.address != null) {
			sql += " AND address=? ";
			params.add(sc.address);
		}
		if (sc.hotelName != null) {
			sql += " AND hotel_name=? ";
			params.add(sc.hotelName);
		}
		if (sc.starLevel >= 0) {
			sql += " AND star_level=? ";
			params.add(sc.starLevel);
		}
		if (sc.score >= 0) {
			sql += " AND score>=? ";
			params.add(sc.score);
		}
		sql += " ORDER BY score DESC";

		ArrayList<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql, params);
		for (Map<String, Object> map : mapList) {
			hotelList.add(getHotelPO(map));
		}
		sqlManager.releaseAll();

		return hotelList;
	}

	@Override
	public ResultMessage_Hotel changeHotelInfo(HotelPO po) throws RemoteException {
		if (po == null)
			return null;

		sqlManager.getConnection();

		String sql = "UPDATE hotel SET hotel_name=?, address=?, region=?, business_district=? WHERE hotel_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelName());
		params.add(po.getAddress());
		params.add(po.getRegion());
		params.add(po.getBusinessDistrict());
		params.add(po.getHotelID());

		boolean isSuccess = sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		if (!isSuccess)
			return ResultMessage_Hotel.Hotel_Not_Exists;
		return ResultMessage_Hotel.Change_Successful;
	}

	@Override
	public ArrayList<HotelEvaluationPO> getHotelEvaluation(String hotelID) throws RemoteException {
		sqlManager.getConnection();

		ArrayList<HotelEvaluationPO> evaluationList = new ArrayList<HotelEvaluationPO>();
		String sql = "SELECT * FROM hotel_evaluation WHERE hotel_id=? ORDER BY time DESC";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[] { hotelID });
		for (Map<String, Object> map : mapList) {
			evaluationList.add(getHotelEvaluationPO(map));
		}
		sqlManager.releaseAll();

		return evaluationList;
	}

	@Override
	public ResultMessage_Hotel evaluation(HotelEvaluationPO po) throws RemoteException {
		if (po == null)
			return null;
		// 酒店id不存在
		if (getHotelInfo(po.getHotelID()) == null)
			return ResultMessage_Hotel.Hotel_Not_Exists;

		sqlManager.getConnection();

		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getClientID());
		params.add(po.getEvaluateTime());
		params.add(po.getEvaluateScore());
		params.add(po.getEvaluateInfo());

		String sql = sqlManager.appendSQL("INSERT INTO hotel_evaluation VALUES ", params.size());

		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		// 更新酒店总评分
		updateHotelScore(po.getHotelID(), po.getEvaluateScore());

		return ResultMessage_Hotel.Evaluate_Successful;
	}

	@Override
	public ResultMessage_Hotel addHotel(HotelPO po) throws RemoteException {
		if (po == null)
			return ResultMessage_Hotel.Add_Hotel_Successful;
		if (getHotelInfo(po.getHotelID()) != null)
			return ResultMessage_Hotel.Hotel_Already_Exists;

		sqlManager.getConnection();

		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getHotelName());
		params.add(po.getAddress());
		params.add(po.getRegion());
		params.add(po.getBusinessDistrict());
		params.add(po.getStarLevel());
		params.add(po.getScore());
		// 默认获得总评价数
		params.add(1);

		String sql = sqlManager.appendSQL("INSERT INTO hotel VALUES ", params.size());

		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();

		return ResultMessage_Hotel.Add_Hotel_Successful;
	}

	/**
	 * 获得酒店评分和总评分数
	 */
	private Map<String, Object> getScoreAndTotalEvaluation(String hotelID) {
		sqlManager.getConnection();

		String sql = "SELECT score, total_evaluation FROM hotel WHERE hotel_id=?";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[] { hotelID });
		sqlManager.releaseAll();

		return map;
	}

	/**
	 * 更新酒店总评分
	 * @param hotelID
	 * @param newScore
	 */
	private void updateHotelScore(String hotelID, int newScore) {
		Map<String, Object> map = getScoreAndTotalEvaluation(hotelID);
		double score = Double.parseDouble(map.get("score").toString());
		int totalEvaluation = Integer.parseInt(map.get("total_evaluation").toString());

		sqlManager.getConnection();

		String sql = "UPDATE hotel SET score=?, total_evaluation=? WHERE hotel_id=?";

		score = (double) (score * totalEvaluation + newScore) / (totalEvaluation + 1);
		totalEvaluation++;

		sqlManager.executeUpdate(sql, new Object[] { score, totalEvaluation, hotelID });
		sqlManager.releaseConnection();

	}

	private HotelPO getHotelPO(Map<String, Object> map) {
		if (map.size() == 0)
			return null;
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
		if (map.size() == 0)
			return null;
		HotelEvaluationPO po = new HotelEvaluationPO();

		po.setHotelID(map.get("hotel_id").toString());
		po.setClientID(map.get("client_id").toString());
		po.setEvaluateTime(map.get("time").toString());
		po.setEvaluateScore(Integer.parseInt(map.get("score").toString()));
		po.setEvaluateInfo(map.get("evaluate_info").toString());

		return po;
	}

}
