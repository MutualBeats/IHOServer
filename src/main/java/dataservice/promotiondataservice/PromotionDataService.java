package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.promotion.PromotionPO;
import po.user.ClientPO;
import util.resultmessage.ResultMessage_Promotion;

public interface PromotionDataService extends Remote{
	/*
	 * 添加酒店促销策略
	 */
	public ResultMessage_Promotion hotelAdd (PromotionPO po) throws RemoteException;
	/*
	 * 添加网站促销策略
	 */
	public ResultMessage_Promotion webAdd (PromotionPO po) throws RemoteException;
	/*
	 * 查看酒店促销策略
	 */
	public ArrayList<PromotionPO> hotelLook (String hotelId) throws RemoteException;
	/*
	 * 查看网站促销策略
	 */
	public ArrayList<PromotionPO> webLook () throws RemoteException;
	/*
	 * 制定会员等级制度
	 */
	public  void  levelMake (PromotionPO po,ClientPO po2)throws RemoteException ;
}
