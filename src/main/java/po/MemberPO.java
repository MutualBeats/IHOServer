package po;

public class MemberPO {
	private String memberID;
	private String memberMessage;
	private int level;
	
	/**
	 * @param memberID
	 * @param memberMessage
	 * @param level
	 */
	public MemberPO(String memberID, String memberMessage, int level) {
		super();
		this.memberID = memberID;
		this.memberMessage = memberMessage;
		this.level = level;
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
	
	
}
