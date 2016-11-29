package po.user;

import java.io.Serializable;

import util.user.MemberType;

public class MemberPO implements Serializable {
	/**
	 * version id
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 用户ID
	 */
	private String clientID;
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
	 * @param memberType
	 * @param level
	 * @param memberMessage
	 */
	public MemberPO(String clientID, MemberType memberType, int level, String memberMessage) {
		super();
		this.clientID = clientID;
		this.memberType = memberType;
		this.level = level;
		this.memberMessage = memberMessage;
	}

	public MemberPO(){
	}
	
	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
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
