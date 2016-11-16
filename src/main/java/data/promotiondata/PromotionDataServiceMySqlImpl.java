/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.promotiondata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.promotiondataservice.PromotionDataService;
import po.MemberPO;
import po.PromotionPO;
import util.ResultMessage;

public class PromotionDataServiceMySqlImpl implements PromotionDataService {

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#hotelAdd(po.PromotionPO)
	 */
	@Override
	public ResultMessage hotelAdd(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#webAdd(po.PromotionPO)
	 */
	@Override
	public ResultMessage webAdd(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#hotelLook(java.lang.String)
	 */
	@Override
	public ArrayList<PromotionPO> hotelLook(String hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#webLook()
	 */
	@Override
	public ArrayList<PromotionPO> webLook() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#levelMake(po.PromotionPO, po.MemberPO)
	 */
	@Override
	public void levelMake(PromotionPO po, MemberPO po2) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
