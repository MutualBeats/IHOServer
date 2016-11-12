package dataservice.orderdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrderPO;
import util.MemberType;
import util.OrderState;
import util.ResultMessage;
import util.RoomType;

public class OrderDataService_Stub implements OrderDataService{
	
	String orderId;
	String userId;
	String userName;
	String userPhone;
	MemberType memberType;
	OrderState orderState;
	String hotelId;
	String hotelName;
	String roomId;
	double value;
	int creditChange;
	String makeTime;
	String inTime;
	String outTime;
	String oInTime;
	String oOutTime;
	String finishTime;
	RoomType roomType;
	String latestETime;
	boolean children;
	int numOfPeople;
	int numOfRoom;



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
	 * @param roomType
	 * @param latestETime
	 * @param children
	 * @param numOfPeople
	 * @param numOfRoom
	 */
	public OrderDataService_Stub(String orderId, String userId, String userName, String userPhone,
			MemberType memberType, OrderState orderState, String hotelId, String hotelName, String roomId, double value,
			int creditChange, String makeTime, String inTime, String outTime, String oInTime, String oOutTime,
			String finishTime, RoomType roomType, String latestETime, boolean children, int numOfPeople,
			int numOfRoom) {
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

	@Override
	public ResultMessage insert(OrderPO po) throws RemoteException {
		if(po.getHotelName().equals("测试酒店")){
			return ResultMessage.InsertSucceed;
		}
		return ResultMessage.InsertFailed;
	}

	@Override
	public ResultMessage update(OrderPO po) throws RemoteException {
		if(po.getHotelName().equals("测试酒店")){
			return ResultMessage.UpdateSucceed;
		}
		return ResultMessage.UpdateFailed;
	}

	@Override
	public OrderPO findById(String orderId) throws RemoteException {
		return new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children);
	}

	@Override
	public ArrayList<OrderPO> findByUser(String userId) throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		list.add(new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children));
		return list;
	}

	@Override
	public ArrayList<OrderPO> findByRoom(String hotelId, String roomId) throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		list.add(new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children));
		return list;
	}

	@Override
	public ArrayList<OrderPO> findUOByHotel(String hotelId, String userId) throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		list.add(new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children));
		return list;
	}

	@Override
	public ArrayList<OrderPO> findHotelOrder(String hotelId) throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		list.add(new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children));
		return list;
	}
	
	@Override
	public ArrayList<OrderPO> findUnexcutedOrder() throws RemoteException {
		ArrayList<OrderPO> list = new ArrayList<OrderPO>();
		list.add(new OrderPO(orderId, userId, userName, userPhone, memberType, orderState, hotelId, hotelName, roomId, roomType, numOfRoom, value, creditChange, makeTime, inTime, oOutTime, oInTime, oOutTime, finishTime, latestETime, numOfPeople, children));
		return list;
	}

	@Override
	public ResultMessage putUpOrder(String orderId) throws RemoteException {
		return ResultMessage.PutUpSucceed;
	}
	
	
	
}
