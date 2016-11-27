/**
 * @author huangxiao
 * 2016年10月16日
 */
package dataservice.hoteldataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.hotel.HotelEvaluationPO;
import po.hotel.HotelPO;
import util.SearchCondition;

public class HotelDataServiceImpl_Stub implements HotelDataService {

	@Override
	public HotelPO getHotelInfo(String hotelID) throws RemoteException {
		System.out.println("Find Succeed!\n");
		HotelPO po = new HotelPO(hotelID, "锦都金鼎大酒店", "如皋市如城镇解放路(如皋市政府对面)", "如皋市", "如城街道", 5, 4.5);
		return po;
	}

	@Override
	public ArrayList<HotelPO> findHotelByCondition(SearchCondition sc) throws RemoteException {
		System.out.println("Find Succeed!\n");
		ArrayList<HotelPO> hotelList = new ArrayList<HotelPO>();
		HotelPO po = new HotelPO("00000001", sc.hotelName == null ? "锦都金鼎大酒店" : sc.hotelName,
					"如皋市如城镇解放路(如皋市政府对面)", sc.region, sc.businessDistrict, sc.starLevel > 0 ? sc.starLevel : 5,
						sc.score > 0 ? sc.score : 4.5);
		hotelList.add(po);
		return hotelList;
	}

	@Override
	public ResultMessage_Hotel changeHotelInfo(HotelPO po) throws RemoteException {
		System.out.println("Update Hotel Data Succeed!\n");
		return ResultMessage_Hotel.Change_Successful;
//		return null;
	}

	@Override
	public ResultMessage_Hotel evaluation(HotelEvaluationPO po) throws RemoteException {
		System.out.println("Insert Hotel Evaluation Succeed!\n");
		return ResultMessage_Hotel.Evaluate_Successful;
	}

	@Override
	public ResultMessage_Hotel addHotel(HotelPO po) throws RemoteException {
		System.out.println("Insert Hotel Succeed!");
		return ResultMessage_Hotel.Add_Hotel_Successful;
	}

	@Override
	public ArrayList<HotelEvaluationPO> getHotelEvaluation(String hotelID) throws RemoteException {
		return null;
	}

}
