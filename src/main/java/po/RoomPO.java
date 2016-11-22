/**
 * @author huangxiao
 * @version 2016年10月15日 po创建
 * @version 2016年11月19日 添加空构造方法
 * @version 2016年11月22日 po属性修改
 */
package po;

import util.RoomCondition;
import util.RoomType;

public class RoomPO {
	/**
	 * 所属酒店id
	 */
	private String hotelID;
	/**
	 * 房间号
	 */
	private String roomNumber;
	/**
	 * 房间类型
	 */
	private RoomType type;
	/**
	 * 房间价格
	 */
	private int price;
	/**
	 * 当前状态
	 */
	private RoomCondition condition;
	
	/**
	 * @param hotelID
	 * @param roomNumber
	 * @param type
	 * @param price
	 * @param condition
	 */
	public RoomPO(String hotelID, String roomNumber, RoomType type, int price, RoomCondition condition) {
		super();
		this.hotelID = hotelID;
		this.roomNumber = roomNumber;
		this.type = type;
		this.price = price;
		this.condition = condition;
	}

	public RoomPO() {
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public RoomCondition getCondition() {
		return condition;
	}

	public void setCondition(RoomCondition condition) {
		this.condition = condition;
	}

}
