/**
 * @version 2016年11月19日 添加空构造方法
 */
package po.promotion;

import java.io.Serializable;
import java.util.ArrayList;

import util.promotion.PromotionType;

public class PromotionPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 促销策略id
	 */
	private String promotionID;
	/**
	 * 促销策略名称
	 */
	private String promotionName;
	/**
	 * 促销策略类型
	 */
	private PromotionType type;
	/**
	 * 不同等级对应折扣
	 */
	private ArrayList<Double> discount;
	/**
	 * 酒店id（若网站促销策略则为空）
	 */
	private String hotelID;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String finishDate;
	
	/**
	 * @param promotionID
	 * @param promotionName
	 * @param type
	 * @param discount
	 * @param hotelID
	 * @param startDate
	 * @param finishDate
	 */
	public PromotionPO(String promotionID, String promotionName, PromotionType type, ArrayList<Double> discount,
			String hotelID, String startDate, String finishDate) {
		super();
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.type = type;
		this.discount = discount;
		this.hotelID = hotelID;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}
	
	public PromotionPO() {
	}

	public String getPromotionID() {
		return promotionID;
	}

	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public PromotionType getType() {
		return type;
	}

	public void setType(PromotionType type) {
		this.type = type;
	}

	public ArrayList<Double> getDiscount() {
		return discount;
	}

	public void setDiscount(ArrayList<Double> discount) {
		this.discount = discount;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	public ArrayList<String> getDistrictList() {
		DistrictPromotionPO po = (DistrictPromotionPO)this;
		return po.getDistrictList();
	}
	
	public void setDistrictList(ArrayList<String> districtList) {
	}
	
	public ArrayList<String> getEnterpriseList() {
		EnterprisePromotionPO po = (EnterprisePromotionPO)this;
		return po.getEnterpriseList();
	}

	public void setEnterpriseList(ArrayList<String> enterpriseList) {
	}
	
}
