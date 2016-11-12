package dataservice.memberdataservice;

import java.util.ArrayList;

import po.MemberPO;

public class MemberDS_Drive {
	public void drive(MemberDS memberDS){
		MemberPO po1=new MemberPO("1234567890", "腾讯企业",1);
		MemberPO po2=new MemberPO("1234567891", "1997.01.01",5);
		try {
			memberDS.businessRegister(po1);
			memberDS.originalRegister(po2);
			/**
			 * Index 0,1,2,3,4,5,6
			 *           1,  100,
			 *           0-1, 1-100
			 */
			ArrayList<Integer> levelList=new ArrayList<>();
			levelList.add(20);
			memberDS.levelUpdate(levelList, 20);
			memberDS.query("1234567890", po1);
			memberDS.update("1234567891", po2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
