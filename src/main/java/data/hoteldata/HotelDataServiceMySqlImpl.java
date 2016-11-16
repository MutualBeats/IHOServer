/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.hoteldata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.hoteldataservice.HotelDataService;
import po.HotelEvaluationPO;
import po.HotelPO;
import util.SearchCondition;

public class HotelDataServiceMySqlImpl implements HotelDataService {

	/* (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#findHotelData(java.lang.String)
	 */
	@Override
	public HotelPO findHotelData(String hotelID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#findHotelListData(util.SearchCondition)
	 */
	@Override
	public ArrayList<HotelPO> findHotelListData(SearchCondition sc) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#updateHotelData(po.HotelPO)
	 */
	@Override
	public void updateHotelData(HotelPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#insertHotelEvaluation(po.HotelEvaluationPO)
	 */
	@Override
	public void insertHotelEvaluation(HotelEvaluationPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dataservice.hoteldataservice.HotelDataService#insertHotel(po.HotelPO)
	 */
	@Override
	public void insertHotel(HotelPO po) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
