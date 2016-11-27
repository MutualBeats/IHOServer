/**
 * @author huangxiao
 * 2016年10月16日
 */
package util;

import java.io.Serializable;

public class SearchCondition implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 酒店地址
	 */
	public String address;
	/**
	 * 酒店所属地区
	 */
	public String region;
	/**
	 * 酒店所属商圈
	 */
	public String businessDistrict;
	/**
	 * 酒店名称
	 */
	public String hotelName;
	/**
	 * 酒店星级
	 */
	public int starLevel;
	/**
	 * 酒店评分
	 */
	public double score;
	
	/**
	 * 构造方法
	 * @param address
	 * @param region
	 * @param businessDistrict
	 * @param hotelName
	 * @param starLevel
	 * @param score
	 */
	public SearchCondition(String address, String region, String businessDistrict, String hotelName, int starLevel,
			double score) {
		super();
		this.address = address;
		this.region = region;
		this.businessDistrict = businessDistrict;
		this.hotelName = hotelName;
		this.starLevel = starLevel;
		this.score = score;
	}
	
}
