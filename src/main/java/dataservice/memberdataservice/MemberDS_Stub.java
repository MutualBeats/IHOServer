package dataservice.memberdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MemberPO;
import util.ResultMessage;

public class MemberDS_Stub implements MemberDS{

	/*
	 * 注册企业会员
	 */
	@Override
	public ResultMessage businessRegister(MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(po.getMemberMessage().equals("腾讯企业"))
			return ResultMessage.RegisterSuccess;
		else 
			return ResultMessage.RegisterFail;
	}
	/*
	 * 注册普通会员
	 */
	@Override
	public ResultMessage originalRegister(MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(po.getMemberMessage().equals("1997.01.01"))
			return ResultMessage.RegisterSuccess;
		else 
			return ResultMessage.RegisterFail;
		
	}
	/*
	 * 会员信息更新
	 */
	@Override
	public MemberPO update(String name,MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(name.equals(po.getMemberID()))
			return new MemberPO(name,"腾讯企业",1);
		else
			return null;
	}
	/*
	 * 会员等级更新
	 */
	@Override
	public void levelUpdate(ArrayList<Integer> levelList, int level) throws RemoteException {
		// TODO Auto-generated method stub
		int result=levelList.indexOf(level);
		System.out.println("Grade" + result);
	}
	
	/*
	 * 会员信息查看
	 */
	@Override
	public MemberPO query (String name,MemberPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(name.equals(po.getMemberID()))
			return new MemberPO(name,"腾讯企业",1);
		else 
			return null;
	}

}
