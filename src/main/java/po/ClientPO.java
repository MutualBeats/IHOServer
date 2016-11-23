/**
 * @version 2016年10月15日
 * @version 2016年11月19日 添加空构造方法
 * @version 2016年11月22日 member合并
 */
package po;

import util.MemberType;

public class ClientPO {
	/*
	 * 用户ID
	 */
	private String clientID;
	/*
	 * 用户姓名/名称
	 */
	private String clientName;
	/*
	 * 用户联系方式
	 */
	private String contactWay;
	/*
	 * 用户信用值
	 */
	private int credit;
	/*
	 * 用户会员类型
	 */
	private MemberType memberType;
	/*
	 * 用户会员等级
	 */
	private int level;
	/*
	 * 用户会员信息
	 */
	private String memberMessage;

	/**
	 * 构造方法
	 * @param clientID
	 * @param clientname
	 * @param contactWay
	 * @param credit
	 * @param memberType
	 * @param level
	 * @param memberMessage
	 */
	public ClientPO(String clientID, String clientName, String contactWay, int credit, MemberType memberType, int level,
			String memberMessage) {
		super();
		this.clientID = clientID;
		this.clientName = clientName;
		this.contactWay = contactWay;
		this.credit = credit;
		this.memberType = memberType;
		this.level = level;
		this.memberMessage = memberMessage;
	}

	public ClientPO() {
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

	public void setClientName(String clientname) {
		this.clientName = clientname;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(String memberMessage) {
		this.memberMessage = memberMessage;
	}

}
