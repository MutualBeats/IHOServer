/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.promotiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.databaseutility.SqlManager;
import dataservice.promotiondataservice.PromotionDataService;
import po.promotion.PromotionPO;
import po.user.ClientPO;
import util.resultmessage.ResultMessage_Promotion;

public class PromotionDataServiceMySqlImpl extends UnicastRemoteObject implements PromotionDataService {

	private static final long serialVersionUID = 2L;
	
	private SqlManager sqlManager = SqlManager.getInstance();

	public PromotionDataServiceMySqlImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#hotelAdd(po.PromotionPO)
	 */
	@Override
	public ResultMessage_Promotion hotelAdd(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.promotiondataservice.PromotionDataService#webAdd(po.PromotionPO)
	 */
	@Override
	public ResultMessage_Promotion webAdd(PromotionPO po) throws RemoteException {
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
	public void levelMake(PromotionPO po, ClientPO po2) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
