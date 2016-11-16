/**
 * @author huangxiao
 * 2016年11月16日
 */
package data.datahelper;

import dataservice.creditdataservice.CreditDataService;
import dataservice.hoteldataservice.HotelDataService;
import dataservice.orderdataservice.OrderDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.roomdataservice.RoomDataService;
import dataservice.userdataservice.UserDataService;

public interface DataFactory {
	
	public CreditDataService getCreditDatabase();
	
	public HotelDataService getHotelDatabase();
	
	public OrderDataService getOrderDatabase();
	
	public PromotionDataService getPromotionDatabase();
	
	public RoomDataService getRoomDatabase();
	
	public UserDataService getUserDatavase();
	
}
