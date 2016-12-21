/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.promotiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.ID;
import data.databaseutility.SqlManager;
import data.datafactory.DataFactoryMySqlImpl;
import dataservice.promotiondataservice.PromotionDataService;
import po.promotion.DistrictPromotionPO;
import po.promotion.EnterprisePromotionPO;
import po.promotion.PromotionPO;
import util.promotion.PromotionType;
import util.resultmessage.ResultMessage_Promotion;

public class PromotionDataServiceMySqlImpl extends UnicastRemoteObject implements PromotionDataService {

	private static final long serialVersionUID = 2L;
	
	// TODO 配置文件
	private static final int PROMOTION_ID_LENGTH = 6;
	private static final int MAX_VIP_LEVEL = 3;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public PromotionDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	private void addSonTable(String promotionID, PromotionPO po) {
		sqlManager.getConnection();
		if(po.getType().equals(PromotionType.BusinessDistrict)) {
			String sql = "INSERT INTO promotion_district VALUES (?,?)";
			for (String district : po.getDistrictList()) {
				sqlManager.executeUpdate(sql, new Object[]{promotionID, district});
			}
		} else if(po.getType().equals(PromotionType.Enterprise)) {
			String sql = "INSERT INTO promotion_enterprise VALUES (?,?)";
			for (String enterprise : po.getEnterpriseList()) {
				sqlManager.executeUpdate(sql, new Object[]{promotionID, enterprise});
			}
		}
		sqlManager.releaseConnection();
	}

	@Override
	public String addPromotion(PromotionPO po) throws RemoteException {
		sqlManager.getConnection();
		String sql;
		// 添加promotion记录
		sql = "INSERT INTO promotion VALUES ";
		List<Object> params = new ArrayList<Object>();
		params.add(null);
		// promotion_id待定
		params.add("");
		params.add(po.getPromotionName());
		params.add(po.getType().toString());
		params.add(po.getHotelID());
		params.add(po.getStartDate());
		params.add(po.getFinishDate());
		// 不同等级折扣添加
		for (double discount : po.getDiscount()) {
			params.add(discount);
		}

		sql = sqlManager.appendSQL(sql, params.size());
		sqlManager.executeUpdateByList(sql, params);
		
		// 获取数据库自增id
		sql = "SELECT id FROM promotion WHERE promotion_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{""});
		int id = Integer.parseInt(map.get("id").toString());
		// 设置promotion_id
		sql = "UPDATE promotion SET promotion_id=? WHERE id=? ";
		String promotionID = ID.idToString(id, PROMOTION_ID_LENGTH);
		sqlManager.executeUpdate(sql, new Object[]{promotionID, id});
		sqlManager.releaseAll();
		
		// 子表记录添加
		addSonTable(promotionID, po);
		
		return promotionID;
	}

	@Override
	public ArrayList<PromotionPO> getHotelPromotion(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM promotion WHERE hotel_id=? ORDER BY promotion_id DESC ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID});
		sqlManager.releaseAll();
		
		ArrayList<PromotionPO> promotionList = new ArrayList<PromotionPO>();
		
		for (Map<String, Object> map : mapList) {
			promotionList.add(getPromotionPO(map));
		}
		
		return promotionList;
	}

	@Override
	public ArrayList<PromotionPO> getWebPromotion() throws RemoteException {
		sqlManager.getConnection();
		
		String sql = "SELECT * FROM promotion WHERE hotel_id=? ORDER BY promotion_id DESC ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{""});
		sqlManager.releaseAll();
		
		ArrayList<PromotionPO> promotionList = new ArrayList<PromotionPO>();
		
		for (Map<String, Object> map : mapList) {
			promotionList.add(getPromotionPO(map));
		}
		
		return promotionList;
	}
	
	@Override
	public PromotionPO getPromotionById(String promotionID) throws RemoteException {
		sqlManager.getConnection();
		String sql = "SELECT * FROM promotion WHERE promotion_id=? ";
		Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{promotionID});
		sqlManager.releaseAll();
		return getPromotionPO(map);
	}
	
	@Override
	public ResultMessage_Promotion deletePromotion(String promotionID) throws RemoteException {
		sqlManager.getConnection();
		String sql;
		Object[] param = new Object[]{promotionID};
		
		sql = "DELETE FROM promotion WHERE promotion_id=? ";
		boolean res = sqlManager.executeUpdate(sql, param);
		// 错误：促销策略id不存在
		if(!res) {
			sqlManager.releaseConnection();
			return ResultMessage_Promotion.Promotion_Not_Exist;
		}
		
		sql = "DELETE FROM promotion_district WHERE promotion_id=? ";
		sqlManager.executeUpdate(sql, param);
		sql = "DELETE FROM promotion_enterprise WHERE promotion_id=?";
		sqlManager.executeUpdate(sql, param);
		
		sqlManager.releaseConnection();
		
		return ResultMessage_Promotion.Delete_Successful;
	}

	@Override
	public ArrayList<Integer> getMemberLevel() throws RemoteException {
		sqlManager.getConnection();
		ArrayList<Integer> level = new ArrayList<Integer>();
		String sql = "SELECT end_credit FROM member_level WHERE level=? ";
		for (int i = 0; i < MAX_VIP_LEVEL; i++) {
			Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{i});
			level.add(Integer.parseInt(map.get("end_credit").toString()));
		}
		sqlManager.releaseAll();
		return level;
	}

	@Override
	public ArrayList<Double> getMemberDiscount() throws RemoteException {
		sqlManager.getConnection();
		ArrayList<Double> discount = new ArrayList<Double>();
		String sql = "SELECT discount FROM member_level WHERE level=? ";
		for (int i = 0; i <= MAX_VIP_LEVEL; i++) {
			Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{i});
			discount.add(Double.parseDouble(map.get("discount").toString()));
		}
		sqlManager.releaseAll();
		return discount;
	}

	@Override
	public ResultMessage_Promotion levelMake(ArrayList<Integer> level, ArrayList<Double> discount) throws RemoteException {
		sqlManager.getConnection();
		String sql;
		for (int i = 0; i <= MAX_VIP_LEVEL; i++) {
			sql = "UPDATE member_level SET start_credit=?, end_credit=?, discount=? WHERE level=? ";
			List<Object> params = new ArrayList<Object>();
			
			params.add(i == 0 ? null : level.get(i - 1) + 1);
			params.add(i == MAX_VIP_LEVEL ? null : level.get(i));
			params.add(discount.get(i));
			params.add(i);
			
			sqlManager.executeUpdateByList(sql, params);
		}
		sqlManager.releaseConnection();
		
		// 更新所有客户等级
		DataFactoryMySqlImpl.getDataServiceInstance().getMemberLevelUpdate().updateMemberLevel(level);
		
		return ResultMessage_Promotion.Level_Make_Successful;
	}
	
	/**
	 * 根据不同策略类型创建策略对象
	 * @param promotionID
	 * @param type
	 * @return
	 */
	private PromotionPO getPromotionByType(String promotionID, PromotionType type) {
		sqlManager.getConnection();

		PromotionPO po;
		if(type.equals(PromotionType.BusinessDistrict)) {
			po = new DistrictPromotionPO();
			// 获得商圈列表
			String sql = "SELECT * FROM promotion_district WHERE promotion_id=? ";
			List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{promotionID});
			ArrayList<String> districtList = new ArrayList<String>();
			for (Map<String,Object> map : mapList)
				districtList.add(map.get("business_district").toString());
			po.setDistrictList(districtList);
			
		} else if(type.equals(PromotionType.Enterprise)) {
			po = new EnterprisePromotionPO();
			// 获得企业列表
			String sql = "SELECT * FROM promotion_enterprise WHERE promotion_id=? ";
			List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{promotionID});
			ArrayList<String> enterpriseList = new ArrayList<String>();
			for (Map<String,Object> map : mapList)
				enterpriseList.add(map.get("enterprise").toString());
			po.setEnterpriseList(enterpriseList);
			
		} else {
			po = new PromotionPO();
		}
		
		sqlManager.releaseAll();

		po.setPromotionID(promotionID);
		po.setType(type);
		
		return po;
	}
	
	/**
	 * Map转换为PromotionPO
	 * @param map
	 * @return promotionPO
	 */
	private PromotionPO getPromotionPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		String promotionID = map.get("promotion_id").toString();
		PromotionType type = PromotionType.valueOf(map.get("type").toString());
		// 根据促销策略类型创建po对象
		PromotionPO po = getPromotionByType(promotionID, type);
		po.setPromotionName(map.get("promotion_name").toString());
		po.setHotelID(map.get("hotel_id").toString());
		po.setStartDate(map.get("start_date").toString());
		po.setFinishDate(map.get("finish_date").toString());
		// 获取折扣列表
		ArrayList<Double> discount = new ArrayList<Double>();
		for(int i = 0; i <= MAX_VIP_LEVEL; i++) {
			discount.add(Double.parseDouble(map.get("discount_lv" + i).toString()));
		}
		po.setDiscount(discount);
		
		return po;
	}	

}
