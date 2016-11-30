/**
 * @version 2016年11月19日 添加空构造方法
 * @version 2016年11月29日 成员变量修改
 */
package po.order;

import java.io.Serializable;
import java.util.ArrayList;

import util.order.OrderState;

public class OrderPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 3L;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 订单状态
	 */
	private OrderState orderState;
	/**
	 * 用户id
	 */
	private String clientId;
	/**
	 * 酒店id
	 */
	private String hotelId;
	/**
	 * 订单预订房间号列表
	 */
	private ArrayList<String> roomNumberList;
	/**
	 * 使用促销策略列表
	 */
	private ArrayList<String> promotionIDList;
	/**
	 * 订单价格
	 */
	private double value;
	/**
	 * 下单时间
	 */
	private String makeTime;
	/**
	 * 订单执行时间
	 */
	private String executeTime;
	/**
	 * 结束时间（执行完成/撤销）
	 */
	private String finishTime;
	/**
	 * 最晚订单执行时间
	 */
	private String latestETime;
	/**
	 * 预订入住日期
	 */
	private String checkInDate;
	/**
	 * 预订离开日期
	 */
	private String estimateCheckOutDate;
	/**
	 * 实际离开日期
	 */
	private String actualCheckOutDate;
	/**
	 * 预计入住人数
	 */
	private int numOfPeople;
	/**
	 * 有无儿童
	 */
	private boolean children;
	
	/**
	 * @param orderId
	 * @param orderState
	 * @param clientId
	 * @param hotelId
	 * @param roomNumberList
	 * @param promotionIDList
	 * @param value
	 * @param makeTime
	 * @param executeTime
	 * @param finishTime
	 * @param latestETime
	 * @param checkInDate
	 * @param estimateCheckOutDate
	 * @param actualCheckOutDate
	 * @param numOfPeople
	 * @param children
	 */
	public OrderPO(String orderId, OrderState orderState, String clientId, String hotelId,
			ArrayList<String> roomNumberList, ArrayList<String> promotionIDList, double value, String makeTime,
			String executeTime, String finishTime, String latestETime, String checkInDate, String estimateCheckOutDate,
			String actualCheckOutDate, int numOfPeople, boolean children) {
		super();
		this.orderId = orderId;
		this.orderState = orderState;
		this.clientId = clientId;
		this.hotelId = hotelId;
		this.roomNumberList = roomNumberList;
		this.promotionIDList = promotionIDList;
		this.value = value;
		this.makeTime = makeTime;
		this.executeTime = executeTime;
		this.finishTime = finishTime;
		this.latestETime = latestETime;
		this.checkInDate = checkInDate;
		this.estimateCheckOutDate = estimateCheckOutDate;
		this.actualCheckOutDate = actualCheckOutDate;
		this.numOfPeople = numOfPeople;
		this.children = children;
	}

	public OrderPO() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public ArrayList<String> getRoomNumberList() {
		return roomNumberList;
	}

	public void setRoomNumberList(ArrayList<String> roomNumberList) {
		this.roomNumberList = roomNumberList;
	}

	public ArrayList<String> getPromotionIDList() {
		return promotionIDList;
	}

	public void setPromotionIDList(ArrayList<String> promotionIDList) {
		this.promotionIDList = promotionIDList;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getLatestETime() {
		return latestETime;
	}

	public void setLatestETime(String latestETime) {
		this.latestETime = latestETime;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getEstimateCheckOutDate() {
		return estimateCheckOutDate;
	}

	public void setEstimateCheckOutDate(String estimateCheckOutDate) {
		this.estimateCheckOutDate = estimateCheckOutDate;
	}

	public String getActualCheckOutDate() {
		return actualCheckOutDate;
	}

	public void setActualCheckOutDate(String actualCheckOutDate) {
		this.actualCheckOutDate = actualCheckOutDate;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}
	
}
