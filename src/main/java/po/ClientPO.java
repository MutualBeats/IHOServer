/**
 * @version 2016年10月15日
 * @version 2016年11月19日 添加空构造方法
 * @version 2016年11月22日 member合并
 */
package po;

public class ClientPO {
	/*
	 * 用户ID
	 */
	private String clientID;
	/*
	 * 用户姓名/名称
	 */
	private String clientname;
	/*
	 * 用户联系方式
	 */
	private String tel_number;
	/*
	 * 用户信用值
	 */
	private int credit;
	/*
	 * 用户会员ID
	 */
	private String memberID;
	/*
	 * 用户会员信息
	 */
	private String memberMessage;
	/*
	 * 用户会员等级
	 */
	private int level;

	/**
	 * @param clientID
	 * @param clientname
	 * @param tel_number
	 * @param credit
	 * @param memberID
	 * @param memberMessage
	 * @param level
	 */
	public ClientPO(String clientID, String clientname, String tel_number, int credit, String memberID,
			String memberMessage, int level) {
		super();
		this.clientID = clientID;
		this.clientname = clientname;
		this.tel_number = tel_number;
		this.credit = credit;
		this.memberID = memberID;
		this.memberMessage = memberMessage;
		this.level = level;
	}

	public ClientPO() {
	}


	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(String memberMessage) {
		this.memberMessage = memberMessage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}
