package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MemberPO;
import po.PromotionPO;
import util.ResultMessage;

public interface PromotionDataService {
	/*
	 * 添加酒店促销策略
	 */
	public ResultMessage hotelAdd (PromotionPO po) throws RemoteException;
	/*
	 * 添加网站促销策略
	 */
	public ResultMessage webAdd (PromotionPO po) throws RemoteException;
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
	public  void  levelMake (PromotionPO po,MemberPO po2)throws RemoteException ;
}
