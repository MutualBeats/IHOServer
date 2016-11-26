/**
 * @author huangxiao
 * 2016年10月16日
 */
package dataservice.hoteldataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.hotel.HotelEvaluationPO;
import po.hotel.HotelPO;
import util.SearchCondition;

public interface HotelDataService extends Remote{

	public HotelPO getHotelInfo(String hotelID) throws RemoteException;
	
	public ArrayList<HotelPO> findHotelByCondition(SearchCondition sc) throws RemoteException;
	
	public ResultMessage_Hotel changeHotelInfo(HotelPO po) throws RemoteException;
		
	public ResultMessage_Hotel evaluation(HotelEvaluationPO po) throws RemoteException;
	
	public ResultMessage_Hotel addHotel(HotelPO po) throws RemoteException;
	
	public ArrayList<HotelEvaluationPO> getHotelEvaluation(String hotelID) throws RemoteException;

	
}
