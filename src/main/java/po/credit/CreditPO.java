/**
 * @author huangxiao
 * @version 2016年10月15日
 * @version 2016年11月19日 添加空构造方法
 */
package po.credit;

import java.io.Serializable;

import util.CreditChangeAction;

public class CreditPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 客户id
	 */
	private String clientID;

	/**
	 * 信用改变时间
	 */
	private String changeTime;

	/**
	 * 信用改变动作
	 */
	private CreditChangeAction action;

	/**
	 * 订单号（当改变动作为充值信用时可为空）
	 */
	private String orderID;

	/**
	 * 信用改变值
	 */
	private int changeValue;

	/**
	 * 改变后信用值
	 */
	private int credit;

	/**
	 * 构造方法
	 * 
	 * @param clientID
	 * @param changeTime
	 * @param action
	 * @param orderID
	 * @param changeValue
	 * @param credit
	 */
	public CreditPO(String clientID, String changeTime, CreditChangeAction action, String orderID, int changeValue,
			int credit) {
		this.clientID = clientID;
		this.changeTime = changeTime;
		this.action = action;
		this.orderID = orderID;
		this.changeValue = changeValue;
		this.credit = credit;
	}

	public CreditPO() {
	}

	/**
	 * 拷贝构造函数
	 * 
	 * @param po
	 */
	public static CreditPO copy(CreditPO po) {
		CreditPO c_po = new CreditPO();
		c_po.changeTime = po.changeTime;
		c_po.changeValue = po.changeValue;
		c_po.clientID = po.clientID;
		c_po.credit = po.credit;
		c_po.action = po.action;
		c_po.orderID = po.orderID;
		return c_po;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public CreditChangeAction getAction() {
		return action;
	}

	public void setAction(CreditChangeAction action) {
		this.action = action;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public int getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(int changeValue) {
		this.changeValue = changeValue;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}
