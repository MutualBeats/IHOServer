/**
 * @author huangxiao
 * 2016年10月15日
 */
package dataservice.roomdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.RoomPO;
import util.RoomCheckRecord;
import util.RoomConditionOfDate;
import util.RoomType;

public class RoomDataServiceImpl_Driver {
	
	public void drive(RoomDataService roomDataService) throws RemoteException {
		RoomPO po = new RoomPO("00000001", "1024", RoomType.Single, 100, new ArrayList<RoomCheckRecord>(), new ArrayList<RoomConditionOfDate>());
		
		roomDataService.insert(po);
		roomDataService.find("00000001");
		roomDataService.update(po);
	}
	
}
