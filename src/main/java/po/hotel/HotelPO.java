/**
 * @author huangxiao
 * @version 2016年10月16日
 * @version 2016年11月19日 添加空构造方法
 */
package po.hotel;

import java.io.Serializable;

public class HotelPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 酒店id
	 */
	private String hotelID;
	/**
	 * 酒店名称
	 */
	private String hotelName;
	/**
	 * 酒店地址
	 */
	private String address;
	/**
	 * 酒店所属地区
	 */
	private String region;
	/**
	 * 酒店所属商圈
	 */
	private String businessDistrict;
	/**
	 * 酒店星级
	 */
	private int starLevel;
	/**
	 * 酒店评分
	 */
	private double score;
	
	/**
	 * 构造方法
	 * @param hotelID
	 * @param hotelName
	 * @param address
	 * @param region
	 * @param businessDistrict
	 * @param starLevel
	 * @param score
	 */
	public HotelPO(String hotelID, String hotelName, String address, String region, String businessDistrict,
			int starLevel, double score) {
		super();
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.address = address;
		this.region = region;
		this.businessDistrict = businessDistrict;
		this.starLevel = starLevel;
		this.score = score;
	}
	
	public HotelPO() {
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBusinessDistrict() {
		return businessDistrict;
	}

	public void setBusinessDistrict(String businessDistrict) {
		this.businessDistrict = businessDistrict;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
}
