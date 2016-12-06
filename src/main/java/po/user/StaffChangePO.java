/**
 * @author huangxiao
 * @version 2016年12月6日
 */
package po.user;

import java.io.Serializable;

public class StaffChangePO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 酒店工作人员ID
	 */
	private String staffID;
	/**
	 * 酒店工作人员姓名
	 */
	private String staffName;
	/**
	 * Phone
	 */
	private String contactWay;
	
	public StaffChangePO(String staffID, String staffName, String contactWay) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		this.contactWay = contactWay;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

}
