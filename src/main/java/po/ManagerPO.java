/**
 * @version 2016年11月19日 添加空构造方法
 */
package po;

public class ManagerPO {
	/**
	 * 网站管理人员ID
	 */
	private String managerID;
	/**
	 * 网站营销管理人员姓名
	 */
	private String managername;
	
	/**
	 * 构造方法
	 * @param managerID
	 * @param managername
	 */
	public ManagerPO(String managerID, String managername) {
		super();
		this.managerID = managerID;
		this.managername = managername;
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
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
}
