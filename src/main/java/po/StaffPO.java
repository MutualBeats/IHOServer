/**
 * @version 2016年11月19日 添加空构造方法
 */
package po;

public class StaffPO {
	/**
	 * 酒店工作人员ID
	 */
	private String staffID;
	/**
	 * 酒店工作人员姓名
	 */
	private String staffname;
	/**
	 * 酒店工作人员所在酒店ID
	 */
	private String hotelId;
	
	/**
	 * @param staffID
	 * @param staffname
	 * @param hotelname
	 * @param hotelId
	 */
	public StaffPO(String staffID, String staffname, String hotelId) {
		super();
		this.staffID = staffID;
		this.staffname = staffname;
		this.hotelId = hotelId;
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
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
}
