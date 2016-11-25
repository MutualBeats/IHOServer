/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.roomdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.databaseutility.SqlManager;
import dataservice.roomdataservice.ResultMessage_Room;
import dataservice.roomdataservice.RoomDataService;
import po.RoomPO;
import po.RoomRecordPO;
import util.RoomCondition;
import util.RoomType;

public class RoomDataServiceMySqlImpl extends UnicastRemoteObject implements RoomDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public RoomDataServiceMySqlImpl() throws RemoteException {
		super();
	}
	
	// TODO RoomDataService接口修改
	
	@Override
	public ArrayList<RoomPO> getRoom(String hotelID) throws RemoteException {
		sqlManager.getConnection();
		
		ArrayList<RoomPO> roomList = new ArrayList<RoomPO>();
		
		String sql = "SELECT * FROM room WHERE hotel_id=? ";
		List<Map<String, Object>> mapList = sqlManager.queryMulti(sql, new Object[]{hotelID});
		
		for(Map<String, Object> map: mapList) {
			roomList.add(getRoomPO(map));
		}
		sqlManager.releaseAll();
		
		return roomList;
	}

	@Override
	public ResultMessage_Room addRoom(RoomPO po) throws RemoteException {
		if(po == null)
			return null;
		sqlManager.getConnection();
		// TODO 判断房间是否已存在
		
		List<Object> params = new ArrayList<Object>();
		params.add(po.getHotelID());
		params.add(po.getRoomNumber());
		params.add(po.getType().toString());
		params.add(po.getCondition().toString());
		
		String sql = sqlManager.appendSQL("INSERT INTO room VALUES ", params.size());
		
		sqlManager.executeUpdateByList(sql, params);
		sqlManager.releaseConnection();
		// TODO
		return null;
	}

	
	private RoomPO getRoomPO(Map<String, Object> map) {
		if(map.size() == 0)
			return null;
		RoomPO po = new RoomPO();
		po.setHotelID(map.get("hotel_id").toString());
		po.setRoomNumber(map.get("room_number").toString());
		po.setType(RoomType.valueOf(map.get("room_type").toString()));
		po.setPrice(Integer.parseInt(map.get("price").toString()));
		po.setCondition(RoomCondition.valueOf(map.get("condition").toString()));
		return po;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#checkIn(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMessage_Room checkIn(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#checkOut(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMessage_Room checkOut(String hotelID, String roomNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#addRecord(po.RoomRecordPO)
	 */
	@Override
	public ResultMessage_Room addRecord(RoomRecordPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#deleteRecord(java.lang.String)
	 */
	@Override
	public ResultMessage_Room deleteRecord(String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
