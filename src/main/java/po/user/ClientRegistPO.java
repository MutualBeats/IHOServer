/**
 * @author huangxiao
 * @version 2016年12月9日
 */
package po.user;

import java.io.Serializable;

public class ClientRegistPO implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String contactWay;
	private String psw;
	
	public ClientRegistPO(String id, String name, String contactWay, String psw) {
		super();
		this.id = id;
		this.name = name;
		this.contactWay = contactWay;
		this.psw = psw;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}

}
