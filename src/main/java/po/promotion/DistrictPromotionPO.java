/**
 * @author huangxiao
 * @version 2016年12月6日
 */
package po.promotion;

import java.util.ArrayList;

import util.promotion.PromotionType;

public class DistrictPromotionPO extends PromotionPO {
	
	/**
	 * 促销策略作用商圈列表
	 */
	private ArrayList<String> districtList;

	/**
	 * @param promotionID
	 * @param promotionName
	 * @param type
	 * @param discount
	 * @param hotelID
	 * @param startDate
	 * @param finishDate
	 * @param districtList
	 */
	public DistrictPromotionPO(String promotionID, String promotionName, PromotionType type, ArrayList<Double> discount,
			String hotelID, String startDate, String finishDate, ArrayList<String> districtList) {
		super(promotionID, promotionName, type, discount, hotelID, startDate, finishDate);
		this.districtList = districtList;
	}
	
	public DistrictPromotionPO() {
	}

	public ArrayList<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList<String> districtList) {
		this.districtList = districtList;
	}

}
