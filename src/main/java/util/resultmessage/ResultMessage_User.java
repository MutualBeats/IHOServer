package util.resultmessage;

public enum ResultMessage_User {
	LoginSuccess,
	LoginFail,
	UserID_Invalid,//ID非法
	PasswordWrong,//密码错误
	Account_Not_Exist,//账户不存在
	
	GetDataSuccess,
	GetDataFail,

	UpdateSuccess,
	UpdateFail,
	UserName_Invalid,

	Register_Success,
	Regitster_Failed,
	OriginalMember_Exist,
	BusinessMember_Exist,
	
	PEOPLE_ADD_SUCCESS,
	PEOPLE_ADD_FAILED,
	Account_Exist,
	
	HotelID_Invalid,
	Hotel_Not_Exist,
	Hotel_Have_Staff,
	
	Net_Error,
}
