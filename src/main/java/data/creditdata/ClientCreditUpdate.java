/**
 * @author huangxiao
 * @version 2016年12月4日
 */
package data.creditdata;

import util.resultmessage.ResultMessage_User;

public interface ClientCreditUpdate {
	
	public ResultMessage_User creditUpdate(String clientID, int newCredit);

}
