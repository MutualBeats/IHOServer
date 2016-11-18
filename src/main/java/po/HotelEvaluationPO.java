/**
 * @author huangxiao
 * @version 2016年10月16日
 * @version 2016年11月19日 添加空构造方法
 */
package po;

public class HotelEvaluationPO {
	
	/**
	 * 被评论酒店id
	 */
	private String hotelID;
	/**
	 * 评价者
	 */
	private String clientName;
	/**
	 * 评价时间
	 */
	private String evaluateTime;
	/**
	 * 评分
	 */
	private int evaluateScore;
	/**
	 * 评论
	 */
	private String evaluateInfo;
	
	/**
	 * 构造方法
	 * @param hotelID
	 * @param clientName
	 * @param evaluateTime
	 * @param evaluateScore
	 * @param evaluateInfo
	 */
	public HotelEvaluationPO(String hotelID, String clientName, String evaluateTime, int evaluateScore,
			String evaluateInfo) {
		super();
		this.hotelID = hotelID;
		this.clientName = clientName;
		this.evaluateTime = evaluateTime;
		this.evaluateScore = evaluateScore;
		this.evaluateInfo = evaluateInfo;
	}
	
	public HotelEvaluationPO() {
	}
	
	public String getHotelID() {
		return hotelID;
	}
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public int getEvaluateScore() {
		return evaluateScore;
	}
	public void setEvaluateScore(int evaluateScore) {
		this.evaluateScore = evaluateScore;
	}
	public String getEvaluateInfo() {
		return evaluateInfo;
	}
	public void setEvaluateInfo(String evaluateInfo) {
		this.evaluateInfo = evaluateInfo;
	}
	

}
