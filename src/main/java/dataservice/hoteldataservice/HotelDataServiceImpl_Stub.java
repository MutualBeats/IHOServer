/**
 * @author huangxiao
 * 2016年10月16日
 */
package dataservice.hoteldataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.HotelEvaluationPO;
import po.HotelPO;
import util.SearchCondition;

public class HotelDataServiceImpl_Stub implements HotelDataService {

	@Override
	public HotelPO findHotelData(String hotelID) throws RemoteException {
		System.out.println("Find Succeed!\n");
		HotelPO po = new HotelPO(hotelID, "锦都金鼎大酒店", "如皋市如城镇解放路(如皋市政府对面)", "如皋市", "如城街道", 5, 4.5);
		return po;
	}

	@Override
	public ArrayList<HotelPO> findHotelListData(SearchCondition sc) throws RemoteException {
		System.out.println("Find Succeed!\n");
		ArrayList<HotelPO> hotelList = new ArrayList<HotelPO>();
		HotelPO po = new HotelPO("00000001", sc.hotelName == null ? "锦都金鼎大酒店" : sc.hotelName,
					"如皋市如城镇解放路(如皋市政府对面)", sc.region, sc.businessDistrict, sc.starLevel > 0 ? sc.starLevel : 5,
						sc.score > 0 ? sc.score : 4.5);
		hotelList.add(po);
		return hotelList;
	}

	@Override
	public void updateHotelData(HotelPO po) throws RemoteException {
		System.out.println("Update Hotel Data Succeed!\n");
	}

	@Override
	public void insertHotelEvaluation(HotelEvaluationPO po) throws RemoteException {
		System.out.println("Insert Hotel Evaluation Succeed!\n");
	}

	@Override
	public void insertHotel(HotelPO po) throws RemoteException {
		System.out.println("Insert Hotel Succeed!");
	}

}
