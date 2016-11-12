package po;

public class StaffPO {
	/*
	 * 酒店工作人员ID
	 */
	private String staffID;
	/*
	 * 酒店工作人员姓名
	 */
	private String staffname;
	/*
	 * 酒店工作人员所在酒店
	 */
	private String hotelname;
	/*
	 * 酒店工作人员所在酒店ID
	 */
	private String hotelId;
	/**
	 * @param staffID
	 * @param staffname
	 * @param hotelname
	 * @param hotelId
	 */
	public StaffPO(String staffID, String staffname, String hotelname, String hotelId) {
		super();
		this.staffID = staffID;
		this.staffname = staffname;
		this.hotelname = hotelname;
		this.hotelId = hotelId;
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
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
}
