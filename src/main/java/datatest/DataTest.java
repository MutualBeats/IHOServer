/**
 * @author huangxiao
 * @version 2016年11月19日
 */
package datatest;

import java.util.ArrayList;

import data.promotiondata.PromotionDataServiceMySqlImpl;
import po.promotion.DistrictPromotionPO;
import po.promotion.PromotionPO;
import util.Time;
import util.promotion.PromotionType;

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
		
		ArrayList<Integer> credit = new ArrayList<>();
		ArrayList<Double> discount = new ArrayList<>();
		credit.add(1000);
		credit.add(2000);
		credit.add(3000);
		discount.add(10.0);
		discount.add(9.9);
		discount.add(9.8);
		discount.add(9.7);
		
		System.out.println(promotion.levelMake(credit, discount));
		
	}

}
