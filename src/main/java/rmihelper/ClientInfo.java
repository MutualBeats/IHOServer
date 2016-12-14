/**
 * @author huangxiao
 * @version 2016年12月14日
 */
package rmihelper;

import po.user.ClientPO;

public interface ClientInfo {
	
	public ClientPO getClientInfo(String clientID);

}
