package dataservice.userdataservice;


import java.rmi.RemoteException;

import po.ClientPO;
import po.MarketerPO;
import po.MemberPO;
import po.StaffPO;

public class UserDataService_Stub implements UserDataService{
	
	public String clientID; 
	public String clientname;
	public String client_tel_number;
	public int credit;
	
	public String staffID;
	public String staffname;
	public String hotelname;
	
	public String marketerID;
	public String marketername;
	public String marketer_tel_number;
	
	public UserDataService_Stub(String clientID,String clientname,String client_tel_number,int credit,
			String staffID,String staffname,String hotelname, 
			String marketerID,String marketername,String marketer_tel_number){
			this.clientID = clientID;
			this.clientname = clientname;
			this.client_tel_number = client_tel_number;
			this.credit = credit;
			this.staffID = staffID;
			this.staffname = staffname;
			this.hotelname = hotelname;
			this.marketerID = marketerID;
			this.marketername = marketername;
			this.marketer_tel_number = marketer_tel_number;
			}

	@Override
	public ClientPO findClientData(String ClientID) throws RemoteException {
		return new ClientPO("0000000001", "admin", "123456789", 100, new MemberPO("1234567890", "腾讯企业",2));
	}

	@Override
	public StaffPO findStaffData(String StaffID) throws RemoteException {
		return new StaffPO("0000000001", "admin","12345678");
	}

	@Override
	public MarketerPO findMarketerData(String MarketerID) throws RemoteException {
		return new MarketerPO("0000000001", "admin", "123456789");
	}

	@Override
	public void updateClientData(ClientPO po) throws RemoteException {
		System.out.println("Update successfully.");
	}

	@Override
	public void updateStaffData(StaffPO po) throws RemoteException {
		System.out.println("Update successfully.");
	}

	@Override
	public void updateMarketerData(MarketerPO po) throws RemoteException {
		System.out.println("Update successfully.");
	}

	@Override
	public void insertMarketer(MarketerPO po) throws RemoteException {
		System.out.println("Insert successfully.");
	}

	@Override
	public void insertStaff(StaffPO po) throws RemoteException {
		System.out.println("Insert successfully.");
	}

	@Override
	public boolean findClient(String ID, String password) throws RemoteException {
		if(password=="123456")
			return true;
		else
			return false;
	}

	@Override
	public void insertClient(ClientPO po, String password) throws RemoteException {
		System.out.println("Insert successfully.");
	}	
}
