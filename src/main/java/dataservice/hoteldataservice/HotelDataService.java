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

public interface HotelDataService {

	public HotelPO findHotelData(String hotelID) throws RemoteException;
	
	public ArrayList<HotelPO> findHotelListData(SearchCondition sc) throws RemoteException;
	
	public void updateHotelData(HotelPO po) throws RemoteException;
		
	public void insertHotelEvaluation(HotelEvaluationPO po) throws RemoteException;
	
	public void insertHotel(HotelPO po) throws RemoteException;
	
}
