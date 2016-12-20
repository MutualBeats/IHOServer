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
		
		ArrayList<Integer> level = new ArrayList<>();
		level.add(0);
		level.add(200);
		level.add(400);
		ArrayList<Double> discount = new ArrayList<>();
		discount.add(10.0);
		discount.add(9.5);
		discount.add(9.0);
		discount.add(8.5);
		promotion.levelMake(level, discount);
		
		
	}

}
