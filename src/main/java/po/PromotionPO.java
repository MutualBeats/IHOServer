/**
 * @version 2016年11月19日 添加空构造方法
 */
package po;

import java.util.ArrayList;

public class PromotionPO {
	private String promotionId;
	private String message;
	private String id;
	private String startTime;
	private String endTime;
	private double discount;
	private static ArrayList<Integer> levelList;
	
	/**
	 * @param promotionId
	 * @param message
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @param discount
	 * @param levelList
	 */
	public PromotionPO(String promotionId, String message, String id, String startTime, String endTime, double discount) {
		super();
		this.promotionId = promotionId;
		this.message = message;
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.discount = discount;
	}
	
	public PromotionPO() {
	}
	
	public static ArrayList<Integer> getLevelList() {
		return levelList;
	}
	public static void setLevelList(ArrayList<Integer> levelList) {
		PromotionPO.levelList = levelList;
	}
	
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
