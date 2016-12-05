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
	 * 
	 * @param staffID
	 * @param staffname
	 * @param hotelname
	 */
	public MarketerPO(String marketerID, String marketerName, String contactWay) {
		this.marketerID = marketerID;
		this.marketerName = marketerName;
		this.contactWay = contactWay;
	}

	public MarketerPO() {
	}

	public String getMarketerID() {
		return marketerID;
	}

	public void setMarketerID(String marketerID) {
		this.marketerID = marketerID;
	}

	public String getMarketerName() {
		return marketerName;
	}

	public void setMarketerName(String marketerName) {
		this.marketerName = marketerName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

}
