/**
 * @version 2016年11月19日 添加空构造方法
 */
package po.user;

import java.io.Serializable;

public class MarketerPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网站营销人员ID
	 */
	private String marketerID;
	/**
	 * 网站营销人员姓名
	 */
	private String marketerName;
	/**
	 * 网站营销人员联系方式
	 */
	private String contactWay;
	
	/**
	 * 构造方法
	 * @param staffID
	 * @param staffname
	 * @param hotelname
	 */
	public MarketerPO(String marketerID, String marketername, String tel_number){
		this.marketerID = marketerID;
		this.marketerName = marketername;
		this.contactWay = tel_number;
	}
	
	public MarketerPO() {
	}
	
	public String getMarketerID() {
		return marketerID;
	}
	public void setMarketerID(String marketerID) {
		this.marketerID = marketerID;
	}
	public String getMarketername() {
		return marketerName;
	}
	public void setMarketername(String marketername) {
		this.marketerName = marketername;
	}
	public String getTel_number() {
		return contactWay;
	}
	public void setTel_number(String tel_number) {
		this.contactWay = tel_number;
	}
	
}
