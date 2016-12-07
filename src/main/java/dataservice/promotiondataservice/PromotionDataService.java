package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.promotion.PromotionPO;
import util.resultmessage.ResultMessage_Promotion;

public interface PromotionDataService extends Remote{
	/**
	 * 添加促销策略
	 * @param po
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Promotion addPromotion(PromotionPO po) throws RemoteException;
	/**
	 * 查看酒店促销策略
	 * @param hotelID
	 * @return ArrayList
	 * @throws RemoteException
	 */
	public ArrayList<PromotionPO> getHotelPromotion(String hotelID) throws RemoteException;
	/**
	 * 查看网站促销策略
	 * @return ArrayList
	 * @throws RemoteException
	 */
	public ArrayList<PromotionPO> getWebPromotion() throws RemoteException;
	/**
	 * 删除促销策略
	 * @param promotionID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Promotion deletePromotion(String promotionID) throws RemoteException;
	/**
	 * 查看会员等级划分方案
	 * @return ArrayList
	 */
	public ArrayList<Integer> getMemberLevel() throws RemoteException;
	/**
	 * 查看不同等级会员折扣
	 * @return ArrayList
	 */
	public ArrayList<Double> getMemberDiscount() throws RemoteException;
	/**
	 * 制定会员等级制度
	 * @param level
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage_Promotion levelMake(ArrayList<Integer> level, ArrayList<Double> discount) throws RemoteException;
	
}
