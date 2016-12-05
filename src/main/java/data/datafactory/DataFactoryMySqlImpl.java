/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.creditdata.ClientCreditUpdate;
import data.creditdata.CreditDataServiceMySqlImpl;
import data.hoteldata.HotelDataServiceMySqlImpl;
import data.orderdata.OrderDataServiceMySqlImpl;
import data.promotiondata.PromotionDataServiceMySqlImpl;
import data.roomdata.RoomDataServiceMySqlImpl;
import data.service.DataService;
import data.userdata.clientdata.ClientDataServiceMySqlImpl;
import data.userdata.managerdata.ManagerDataServiceMySqlImpl;
import data.userdata.marketerdata.MarketerDataServiceMySqlImpl;
import data.userdata.staffdata.HotelInfo;
import data.userdata.staffdata.StaffDataServiceMySqlImpl;
import data.utildata.UtilDataServiceMySqlImpl;
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
import dataservice.utildataservice.Identify;

public class DataFactoryMySqlImpl extends UnicastRemoteObject implements DataFactory, DataService {
	
	private static final long serialVersionUID = 2L;
	
	private static DataFactoryMySqlImpl dataFactoryMySql;
	
	private DataFactoryMySqlImpl() throws RemoteException {
	}
	
	public static DataFactory getDataFactoryInstance() {
		if(dataFactoryMySql == null)
			try {
				dataFactoryMySql = new DataFactoryMySqlImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		return dataFactoryMySql;
	}
	
	public static DataService getDataServiceInstance() {
		if(dataFactoryMySql == null)
			try {
				dataFactoryMySql = new DataFactoryMySqlImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		return dataFactoryMySql;
	}
	
	private CreditDataServiceMySqlImpl creditDatabase;
	private HotelDataServiceMySqlImpl hotelDatabase;
	private OrderDataServiceMySqlImpl orderDatabase;
	private PromotionDataServiceMySqlImpl promotionDatabase;
	private RoomDataServiceMySqlImpl roomDatabase;
	private ClientDataServiceMySqlImpl clientDatabase;
	private ManagerDataServiceMySqlImpl managerDatabase;
	private MarketerDataServiceMySqlImpl marketerDatabase;
	private StaffDataServiceMySqlImpl staffDatabase;
	private UtilDataServiceMySqlImpl utilDatabase;

	
	@Override
	public CreditDataService getCreditDatabase() throws RemoteException {
		if(creditDatabase == null) {
			creditDatabase = new CreditDataServiceMySqlImpl();
		}
		return creditDatabase;
	}

	@Override
	public HotelDataService getHotelDatabase() throws RemoteException {
		if(hotelDatabase == null) {
			hotelDatabase = new HotelDataServiceMySqlImpl();
		}
		return hotelDatabase;
	}

	@Override
	public OrderDataService getOrderDatabase() throws RemoteException {
		if(orderDatabase == null) {
			orderDatabase = new OrderDataServiceMySqlImpl();
		}
		return orderDatabase;
	}

	@Override
	public PromotionDataService getPromotionDatabase() throws RemoteException {
		if(promotionDatabase == null) {
			promotionDatabase = new PromotionDataServiceMySqlImpl();
		}
		return promotionDatabase;
	}

	@Override
	public RoomDataService getRoomDatabase() throws RemoteException {
		if(roomDatabase == null) {
			roomDatabase = new RoomDataServiceMySqlImpl();
		}
		return roomDatabase;
	}

	@Override
	public ClientDataService getClientDatabase() throws RemoteException {
		if(clientDatabase == null) {
			clientDatabase = new ClientDataServiceMySqlImpl();
		}
		return clientDatabase;
	}

	@Override
	public MarketerDataService getMarketerDatabase() throws RemoteException {
		if(marketerDatabase == null) {
			marketerDatabase = new MarketerDataServiceMySqlImpl();
		}
		return marketerDatabase;
	}

	@Override
	public StaffDataService getStaffDatabase() throws RemoteException {
		if(staffDatabase == null) {
			staffDatabase = new StaffDataServiceMySqlImpl();
		}
		return staffDatabase;
	}

	@Override
	public ManagerDataService getManagerDatabase() throws RemoteException {
		if(managerDatabase == null) {
			managerDatabase = new ManagerDataServiceMySqlImpl();
		}
		return managerDatabase;
	}
	
	@Override
	public Identify getIdentityService() throws RemoteException {
		if(utilDatabase == null) {
			utilDatabase = new UtilDataServiceMySqlImpl();
		}
		return utilDatabase;
	}
	
	@Override
	public HotelInfo getHotelInfo() throws RemoteException {
		if(hotelDatabase == null) {
			hotelDatabase = new HotelDataServiceMySqlImpl();
		}
		return hotelDatabase;
	}
	
	@Override
	public ClientCreditUpdate getClientCreditUpdate() throws RemoteException {
		if(clientDatabase == null) {
			clientDatabase = new ClientDataServiceMySqlImpl();
		}
		return clientDatabase;
	}

}
