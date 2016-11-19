/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.orderdata.OrderDataServiceMySqlImpl;
import data.promotiondata.PromotionDataServiceMySqlImpl;
import data.roomdata.RoomDataServiceMySqlImpl;
import data.userdata.ClientDataServiceMySqlImpl;
import data.userdata.ManagerDataServiceMySqlImpl;
import data.userdata.MarketerDataServiceMySqlImpl;
import data.userdata.StaffDataServiceMySqlImpl;
import dataservice.creditdataservice.CreditDataService;
import dataservice.datafactoryservice.DataFactory;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.orderdataservice.OrderDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.ClientDataService;
import dataservice.userdataservice.ManagerDataService;
import dataservice.userdataservice.MarketerDataService;
import dataservice.userdataservice.StaffDataService;

public class DataFactoryMySqlImpl extends UnicastRemoteObject implements DataFactory {
	
	private static final long serialVersionUID = 2L;
	
	private static DataFactoryMySqlImpl dataFactoryMySql;
	
	private DataFactoryMySqlImpl() throws RemoteException{
	}
	
	public static DataFactory getInstance() {
		if(dataFactoryMySql == null)
			try {
				dataFactoryMySql = new DataFactoryMySqlImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		return dataFactoryMySql;
	}
	
	private CreditDataService creditDatabase = new CreditDataServiceMySqlImpl();
	private HotelDataService hotelDatabase = new HotelDataServiceMySqlImpl();
	private OrderDataService orderDatabase = new OrderDataServiceMySqlImpl();
	private PromotionDataService promotionDatabase = new PromotionDataServiceMySqlImpl();
	private RoomDataService roomDatabase = new RoomDataServiceMySqlImpl();
	private ClientDataService clientDatabase = new ClientDataServiceMySqlImpl();
	private ManagerDataService managerDatabase = new ManagerDataServiceMySqlImpl();
	private MarketerDataService marketerDatabase = new MarketerDataServiceMySqlImpl();
	private StaffDataService staffDatabase = new StaffDataServiceMySqlImpl();


	@Override
	public CreditDataService getCreditDatabase() throws RemoteException {
		return creditDatabase;
	}

	@Override
	public HotelDataService getHotelDatabase() throws RemoteException {
		return hotelDatabase;
	}

	@Override
	public OrderDataService getOrderDatabase() throws RemoteException {
		return orderDatabase;
	}

	@Override
	public PromotionDataService getPromotionDatabase() throws RemoteException {
		return promotionDatabase;
	}

	@Override
	public RoomDataService getRoomDatabase() throws RemoteException {
		return roomDatabase;
	}

	@Override
	public ClientDataService getClientDatabase() throws RemoteException {
		return clientDatabase;
	}

	@Override
	public MarketerDataService getMarketerDatabase() throws RemoteException {
		return marketerDatabase;
	}

	@Override
	public StaffDataService getStaffDatabase() throws RemoteException {
		return staffDatabase;
	}

	@Override
	public ManagerDataService getManagerDatabase() throws RemoteException {
		return managerDatabase;
	}
	
}
