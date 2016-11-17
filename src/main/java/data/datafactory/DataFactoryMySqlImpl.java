/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.datafactory;

import java.rmi.RemoteException;

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
import dataservice.datafactory.DataFactory;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.orderdataservice.OrderDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.ClientDataService;
import dataservice.userdataservice.ManagerDataService;
import dataservice.userdataservice.MarketerDataService;
import dataservice.userdataservice.StaffDataService;

public class DataFactoryMySqlImpl implements DataFactory {
	
	private static DataFactoryMySqlImpl dataFactoryMySql;
	
	private DataFactoryMySqlImpl() {
	}
	
	public static DataFactory getInstance() {
		if(dataFactoryMySql == null)
			dataFactoryMySql = new DataFactoryMySqlImpl();
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
	public CreditDataService getCreditDatabase() {
		return creditDatabase;
	}

	@Override
	public HotelDataService getHotelDatabase() {
		return hotelDatabase;
	}

	@Override
	public OrderDataService getOrderDatabase() {
		return orderDatabase;
	}

	@Override
	public PromotionDataService getPromotionDatabase() {
		return promotionDatabase;
	}

	@Override
	public RoomDataService getRoomDatabase() {
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
