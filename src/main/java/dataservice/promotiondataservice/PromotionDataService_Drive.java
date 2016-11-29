package dataservice.promotiondataservice;

import po.promotion.PromotionPO;
import po.user.ClientPO;
import util.user.MemberType;

public class PromotionDataService_Drive {
	
		public void drive(PromotionDataService promotionDS){
			PromotionPO po1=new PromotionPO("123456789", "双十一全店特价","1234567890","2016/10/10","2016/10/20",0.1);
			PromotionPO po2=new PromotionPO("123456780", "双十一全网特价","123456","2016/10/10","2016/10/20",0.1);
			ClientPO po3=new ClientPO("0000000001", "admin", "123456789", 100, MemberType.Enterprise, 2, "腾讯企业");
			try {
				promotionDS.hotelAdd(po1);
				promotionDS.webAdd(po2);
				promotionDS.hotelLook("12345678");
				promotionDS.webLook();
				promotionDS.levelMake(po1,po3);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
