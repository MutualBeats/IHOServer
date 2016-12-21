/**
 * @version 2016年11月19日 添加空构造方法
 */
package po.user;

import java.io.Serializable;

public class ManagerPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网站管理人员ID
	 */
	private String managerID;
	/**
	 * 网站管理人员姓名
	 */
	private String managerName;
	/**
	 * 网站管理人员联系方式
	 */
	private String contactWay;
	
	/**
	 * @param managerID
	 * @param managerName
	 * @param contactWay
	 */
	public ManagerPO(String managerID, String managerName, String contactWay) {
		super();
		this.managerID = managerID;
		this.managerName = managerName;
		this.contactWay = contactWay;
	}
	
	public ManagerPO() {
	}
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
}
