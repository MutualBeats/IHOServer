/**
 * @author huangxiao
 * @version 2016年12月6日
 */
package po.promotion;

import java.util.ArrayList;

import util.promotion.PromotionType;

public class EnterprisePromotionPO extends PromotionPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 促销策略合作企业列表
	 */
	private ArrayList<String> enterpriseList;

	/**
	 * @param promotionID
	 * @param promotionName
	 * @param type
	 * @param discount
	 * @param hotelID
	 * @param startDate
	 * @param finishDate
	 * @param enterpriseList
	 */
	public EnterprisePromotionPO(String promotionID, String promotionName, PromotionType type,
			ArrayList<Double> discount, String hotelID, String startDate, String finishDate,
			ArrayList<String> enterpriseList) {
		super(promotionID, promotionName, type, discount, hotelID, startDate, finishDate);
		this.enterpriseList = enterpriseList;
	}
	
	public EnterprisePromotionPO() {
	}

	public ArrayList<String> getEnterpriseList() {
		return enterpriseList;
	}

	public void setEnterpriseList(ArrayList<String> enterpriseList) {
		this.enterpriseList = enterpriseList;
	}

}
