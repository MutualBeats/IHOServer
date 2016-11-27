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
	 * 网站营销管理人员姓名
	 */
	private String managerName;
	
	/**
	 * 构造方法
	 * @param managerID
	 * @param managername
	 */
	public ManagerPO(String managerID, String managername) {
		super();
		this.managerID = managerID;
		this.managerName = managername;
	}
	
	public ManagerPO() {
	}
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getManagername() {
		return managerName;
	}
	public void setManagername(String managername) {
		this.managerName = managername;
	}
}
