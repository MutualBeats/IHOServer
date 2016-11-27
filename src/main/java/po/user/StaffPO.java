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
	 * 酒店工作人员所在酒店ID
	 */
	private String hotelID;
	
	/**
	 * @param staffID
	 * @param staffname
	 * @param hotelname
	 * @param hotelId
	 */
	public StaffPO(String staffID, String staffname, String hotelId) {
		super();
		this.staffID = staffID;
		this.staffName = staffname;
		this.hotelID = hotelId;
	}
	
	public StaffPO() {
	}
	
 	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getStaffname() {
		return staffName;
	}
	public void setStaffname(String staffname) {
		this.staffName = staffname;
	}
	public String getHotelId() {
		return hotelID;
	}
	public void setHotelId(String hotelId) {
		this.hotelID = hotelId;
	}
	
}
