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
import util.hotel.SearchCondition;
import util.resultmessage.ResultMessage_Hotel;

public interface HotelDataService extends Remote{

	/**
	 * 获取酒店信息
	 * @param hotelID
	 * @return HotelPO
	 * @throws RemoteException
	 */
	public HotelPO getHotelInfo(String hotelID) throws RemoteException;
	/**
	 * 搜索酒店
	 * @param sc
	 * @return ArrayList<HotelPO>
	 * @throws RemoteException
	 */
	public ArrayList<HotelPO> findHotelByCondition(SearchCondition sc) throws RemoteException;
	/**
	 * 更改酒店信息
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Hotel changeHotelInfo(HotelPO po) throws RemoteException;
	/**
	 * 评价酒店
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Hotel evaluation(HotelEvaluationPO po) throws RemoteException;
	/**
	 * 添加酒店
	 * @param po
	 * @return hotelID
	 * @throws RemoteException
	 */
	public String addHotel(HotelPO po) throws RemoteException;
	/**
	 * 获取酒店评价
	 * @param hotelID
	 * @return ArrayList<HotelEvaluationPO>
	 * @throws RemoteException
	 */
	public ArrayList<HotelEvaluationPO> getHotelEvaluation(String hotelID) throws RemoteException;
	
}
