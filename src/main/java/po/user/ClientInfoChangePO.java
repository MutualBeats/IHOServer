/**
 * @author huangxiao
 * @version 2016年12月5日
 */
package po.user;

import java.io.Serializable;

public class ClientInfoChangePO implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	private String clientID;
	
	private String clientName;
	
	private String contactWay;

	public ClientInfoChangePO(String clientID, String clientName, String contactWay) {
		super();
		this.clientID = clientID;
		this.clientName = clientName;
		this.contactWay = contactWay;
	}
	

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
}
