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
import dataservice.roomdataservice.RoomDataService;
import po.CreditPO;
import po.RoomPO;

public class RoomDataServiceMySqlImpl extends UnicastRemoteObject implements RoomDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public RoomDataServiceMySqlImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#find(java.lang.String)
	 */
	@Override
	public ArrayList<RoomPO> find(String hotelID) throws RemoteException {
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

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#insert(po.RoomPO)
	 */
	@Override
	public void insert(RoomPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.roomdataservice.RoomDataService#update(po.RoomPO)
	 */
	@Override
	public void update(RoomPO po) throws RemoteException {
		// TODO Auto-generated method stub
	}
	
	private RoomPO getRoomPO(Map<String, Object> map) {
		// TODO
		return null;
	}

}
