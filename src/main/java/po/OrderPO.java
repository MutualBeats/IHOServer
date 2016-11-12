package po;

import util.MemberType;
import util.OrderState;
import util.RoomType;

public class OrderPO {
	
	
	/**
	 * @param orderId
	 * @param userId
	 * @param userName
	 * @param userPhone
	 * @param memberType
	 * @param orderState
	 * @param hotelId
	 * @param hotelName
	 * @param roomId
	 * @param value
	 * @param creditChange
	 * @param makeTime
	 * @param inTime
	 * @param outTime
	 * @param oInTime
	 * @param oOutTime
	 * @param finishTime
	 */
	public OrderPO(String orderId, String userId, String userName, String userPhone, MemberType memberType,
			OrderState orderState, String hotelId, String hotelName, String roomId,RoomType roomType, int numOfRoom, double value, int creditChange,
			String makeTime, String inTime, String outTime, String oInTime, String oOutTime, String finishTime, String latestETime, int numOfPeople, boolean children) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.memberType = memberType;
		this.orderState = orderState;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.roomId = roomId;
		this.value = value;
		this.creditChange = creditChange;
		this.makeTime = makeTime;
		this.inTime = inTime;
		this.outTime = outTime;
		this.oInTime = oInTime;
		this.oOutTime = oOutTime;
		this.finishTime = finishTime;
		this.roomType = roomType;
		this.latestETime = latestETime;
		this.children = children;
		this.numOfPeople = numOfPeople;
		this.numOfRoom = numOfRoom;
	}
	/*
	 * 订单id
	 */
	private String orderId;
	/*
	 * 用户id 
	 */
	private String userId;
	/*
	 * 用户名称
	 */
	private String userName;
	/*
	 * 用户联系方式
	 */
	private String userPhone;
	/*
	 * 会员类型
	 */
	private MemberType memberType;
	/*
	 * 订单状态
	 */
	private OrderState orderState;
	/*
	 * 酒店id
	 */
	private String hotelId;
	/*
	 * 酒店名称
	 */
	private String hotelName;
	/*
	 * 房间Id
	 */
	private String roomId;
	/*
	 * 房间类型
	 */
	private RoomType roomType;
	/*
	 * 房间数量
	 */
	private int numOfRoom;
	/*
	 * 订单价格
	 */
	private double value;
	/*
	 * 信用值变化
	 */
	private int creditChange;
	/*
	 * 订单生成时间
	 */
	private String makeTime;
	/*
	 * 最晚订单执行时间
	 */
	private String latestETime;
	/*
	 * 订单执行时间
	 */
	private String inTime;
	/*
	 * 订单执行完毕时间
	 */
	private String outTime;
	/*
	 * 预订入住时间
	 */
	private String oInTime;
	/*
	 * 预订离开时间
	 */
	private String oOutTime;
	/*
	 * 订单完成时间
	 */
	private String finishTime;
	/*
	 * 预计入住人数
	 */
	private int numOfPeople;
	/*
	 * 有无儿童
	 */
	private boolean children;
	
	public int getNumOfRoom() {
		return numOfRoom;
	}
	public void setNumOfRoom(int numOfRoom) {
		this.numOfRoom = numOfRoom;
	}
	public String getLatestETime() {
		return latestETime;
	}
	public void setLatestETime(String latestETime) {
		this.latestETime = latestETime;
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
	public String getOrderId() {
		return orderId;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public MemberType getMemberType() {
		return memberType;
	}
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	public OrderState getOrderState() {
		return orderState;
	}
	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getCreditChange() {
		return creditChange;
	}
	public void setCreditChange(int creditChange) {
		this.creditChange = creditChange;
	}
	public String getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getoInTime() {
		return oInTime;
	}
	public void setoInTime(String oInTime) {
		this.oInTime = oInTime;
	}
	public String getoOutTime() {
		return oOutTime;
	}
	public void setoOutTime(String oOutTime) {
		this.oOutTime = oOutTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
}
