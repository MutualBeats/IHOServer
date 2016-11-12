/**
 * @author huangxiao
 * 2016年10月15日
 */
package po;

public class CreditPO {
	/**
	 * 客户id
	 */
	private String clientID;
	
	/**
	 * 信用改变时间
	 */
	private String changeTime;
	
	/**
	 * 信用改变值
	 */
	private int changeValue;
	
	/**
	 * 改变后信用值
	 */
	private int credit;
	
	/**
	 * 构造方法
	 * @param clientID
	 * @param changeTime
	 * @param changeValue
	 * @param credit
	 */
	public CreditPO(String clientID, String changeTime, int changeValue, int credit) {
		this.clientID = clientID;
		this.changeTime = changeTime;
		this.changeValue = changeValue;
		this.credit = credit;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public int getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(int changeValue) {
		this.changeValue = changeValue;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
}
