/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.promotiondata.PromotionDataServiceMySqlImpl;

public class DataTest {
	
	public static void main(String[] args) throws Exception {

		PromotionDataServiceMySqlImpl promotion = new PromotionDataServiceMySqlImpl();
		
//		ArrayList<String> districtList = new ArrayList<>();
//		districtList.add("如城镇");
//		districtList.add("栖霞区");
//		
//		PromotionPO po = new DistrictPromotionPO();
//		po.setPromotionName("商圈活动");
//		po.setType(PromotionType.BusinessDistrict);
//		po.setDistrictList(districtList);
//		po.setHotelID("");
//		po.setStartDate(Time.getCurrentDate());
//		po.setFinishDate(Time.getCurrentDate());
//		
//		System.out.println(promotion.addPromotion(po));
		
		System.out.println(promotion.deletePromotion("abc"));
		
	}

}
