/**
 * @version 2016年11月19日 添加空构造方法
 */
package po.user;

import java.io.Serializable;

public class StaffPO implements Serializable {
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
	/**
	 * 酒店工作人员所在酒店ID
	 */
	private String hotelID;
	
	/**
	 * @param staffID
	 * @param staffName
	 * @param contactWay
	 * @param hotelID
	 */
	public StaffPO(String staffID, String staffName, String contactWay, String hotelID) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		this.contactWay = contactWay;
		this.hotelID = hotelID;
	}
	
	public StaffPO() {
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

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
