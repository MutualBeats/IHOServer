package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ClientPO;
import po.PromotionPO;
import util.ResultMessage;

public class PromotionDataService_Stub implements PromotionDataService{

	/*
	 * 添加酒店促销策略
	 */
	@Override
	public ResultMessage hotelAdd(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(po.getMessage().equals("双十一全店特价"))
			return ResultMessage.HotelAddSuccess;
		else
			return ResultMessage.HotelAddFail;
	}

	/*
	 * 添加网站促销策略
	 */
	@Override
	public ResultMessage webAdd(PromotionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(po.getMessage().equals("双十一全网特价"))
			return ResultMessage.WebAddSuccess;
		else
			return ResultMessage.WebAddFail;
	}


	/*
	 * 制定会员等级制度
	 */
	@Override
	public void levelMake(PromotionPO po,ClientPO po2) throws RemoteException {
		// TODO Auto-generated method stub
		if(PromotionPO.getLevelList().contains(po2.getLevel()))
			System.out.println("The level is "+ PromotionPO.getLevelList().indexOf(po2));
	}

	@Override
	public ArrayList<PromotionPO> hotelLook(String hotelId) throws RemoteException {
		PromotionPO po1=new PromotionPO("123456789", "双十一全店特价","1234567890","2016/10/10","2016/10/20",0.1);
//		PromotionPO po2=new PromotionPO("123456780", "双十一全网特价","123456","2016/10/10","2016/10/20",0.1);
		ArrayList<PromotionPO> list = new ArrayList<>();
		list.add(po1);
		return list;
	}

	@Override
	public ArrayList<PromotionPO> webLook() throws RemoteException {
		PromotionPO po1=new PromotionPO("123456789", "双十一全店特价","1234567890","2016/10/10","2016/10/20",0.1);
		PromotionPO po2=new PromotionPO("123456780", "双十一全网特价","123456","2016/10/10","2016/10/20",0.1);
		ArrayList<PromotionPO> list = new ArrayList<>();
		list.add(po1);
		list.add(po2);
		return list;
	}

}
