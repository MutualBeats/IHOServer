/**
 * @author huangxiao
 * @version 2016年12月23日
 */
package data.userdata.clientdata;

import java.rmi.RemoteException;

public interface GetMemberLevel {
	
	/**
	 * 获取信用值对应会员等级
	 * @param credit
	 * @return memberLevel
	 */
	public int getMemberLevel(int credit) throws RemoteException;

}
