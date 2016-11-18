/**
 * @version 2016年10月15日
 * @version 2016年11月19日 添加空构造方法
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
	 *用户会员信息 
	 */
	private MemberPO member;

	/**
	 * @param clientID
	 * @param clientname
	 * @param tel_number
	 * @param credit
	 * @param member
	 */
	public ClientPO(String clientID, String clientname, String tel_number, int credit, MemberPO member) {
		super();
		this.clientID = clientID;
		this.clientname = clientname;
		this.tel_number = tel_number;
		this.credit = credit;
		this.member = member;
	}
	
	public ClientPO() {
	}
	
	public MemberPO getMember() {
		return member;
	}
	public void setMember(MemberPO member) {
		this.member = member;
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

