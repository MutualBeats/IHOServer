package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.ClientPO;
import po.MarketerPO;
import po.MemberPO;
import po.StaffPO;

public class UserDataService_Driver {
	public void drive(UserDataService userDataService) throws RemoteException{
		/*
		 * 获取ClientPO内部数据
		 */
		ClientPO po1 = userDataService.findClientData("0000000001");
		System.out.println(po1.getClientID());
		System.out.println(po1.getClientname());
		System.out.println(po1.getTel_number());
		System.out.println(po1.getCredit());
		/*
		 * 获取StaffPO内部数据
		 */
		StaffPO po2 = userDataService.findStaffData("0000000001");
		System.out.println(po2.getStaffID());
		System.out.println(po2.getStaffname());
		System.out.println(po2.getHotelId());
		/*
		 * 获取MarketerPO内部数据
		 */
		MarketerPO po3 = userDataService.findMarketerData("0000000001");
		System.out.println(po3.getMarketerID());
		System.out.println(po3.getMarketername());
		System.out.println(po3.getTel_number());
		/*
		 * 在数据库中更新一个ClientPO记录
		 */
		userDataService.updateClientData(new ClientPO("0000000001", "admin", "123456789", 100, new MemberPO("1234567890", "腾讯企业",2)));
		/*
		 * 在数据库中更新一个StaffPO记录
		 */
		userDataService.updateStaffData(new StaffPO("0000000001", "admin", "12345678"));
		/*
		 * 在数据库中更新一个ClientPO记录
		 */
		userDataService.updateMarketerData(new MarketerPO("0000000001", "admin", "123456789"));
		/*
		 * 在数据库中生成一个marketerPO记录
		 */
		userDataService.insertMarketer(new MarketerPO("0000000001", "admin", "123456789"));
		/*
		 * 在数据库中生成一个staffPO记录
		 */
		userDataService.insertStaff(new StaffPO("0000000001", "admin", "12345678"));
		/*
		 * 检测是否允许登陆
		 */
		boolean state = userDataService.findClient("0000000001", "123456");
		if(state)
			System.out.println("Login successfully.");
		else
			System.out.println("Login failed.");
		/*
		 * 在数据库中生成一个clientPO记录
		 */
		userDataService.insertClient(new ClientPO("0000000001", "admin", "123456789", 100, new MemberPO("1234567890", "腾讯企业",2)), "123456");
	}
}
