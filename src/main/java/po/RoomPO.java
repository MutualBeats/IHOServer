/**
 * @author huangxiao
 * @version 2016年10月15日
 * @version 2016年11月19日 添加空构造方法
 */
package po;

import java.util.ArrayList;

import util.RoomCheckRecord;
import util.RoomConditionOfDate;
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
	 * 房间记录
	 */
	private ArrayList<RoomCheckRecord> checkRecordList;
	/**
	 * 房间状态
	 */
	private ArrayList<RoomConditionOfDate> conditionList;
	
	/**
	 * 构造方法
	 * @param hotelID
	 * @param roomNumber
	 * @param type
	 * @param price
	 * @param checkRecordList
	 * @param conditionList
	 */
	public RoomPO(String hotelID, String roomNumber, RoomType type, int price, 
				ArrayList<RoomCheckRecord> checkRecordList, ArrayList<RoomConditionOfDate> conditionList) {
		this.hotelID = hotelID;
		this.roomNumber = roomNumber;
		this.type = type;
		this.price = price;
		this.checkRecordList = checkRecordList;
		this.conditionList = conditionList;
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

	public ArrayList<RoomCheckRecord> getCheckRecordList() {
		return checkRecordList;
	}

	public void setCheckRecordList(ArrayList<RoomCheckRecord> checkRecordList) {
		this.checkRecordList = checkRecordList;
	}

	public ArrayList<RoomConditionOfDate> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<RoomConditionOfDate> conditionList) {
		this.conditionList = conditionList;
	}
	
}
