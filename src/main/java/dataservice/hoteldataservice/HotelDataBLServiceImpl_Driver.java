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

public class HotelDataBLServiceImpl_Driver {

	public void driver(HotelDataService hotelDataService) throws RemoteException {
		HotelPO hotelPO = new HotelPO("00000001", "锦都金鼎大酒店", "如皋市如城镇解放路(如皋市政府对面)", "如皋市", "如城街道", 5, 4.5);
		HotelEvaluationPO evaluationPO = new HotelEvaluationPO("00000001", "丁二玉", "2016/10/16", 5, "强，无敌！");
		
		ArrayList<HotelPO> hotelList = new ArrayList<HotelPO>();
		hotelList.add(hotelPO);
		ArrayList<HotelEvaluationPO> evaluationList = new ArrayList<HotelEvaluationPO>();
		evaluationList.add(evaluationPO);
		
		hotelDataService.insertHotel(hotelPO);
		hotelDataService.insertHotelEvaluation(evaluationPO);
		hotelDataService.findHotelData("00000001");
		
		SearchCondition sc = new SearchCondition(null, "如皋市", "如城街道", "锦都金鼎大酒店", 0, 0);
		hotelDataService.findHotelListData(sc);
		
		hotelDataService.updateHotelData(hotelPO);
	}
	
}
