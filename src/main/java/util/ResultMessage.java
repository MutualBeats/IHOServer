package util;

public enum ResultMessage {
	MakeSucceed,
	MakeFailed,
	CancelSucceed,
	CancelFailed,
	ExcuteSucceed,
	ExcuteFailed, 
	PutUpSucceed,
	PutUpFailed,
	InsertSucceed,
	InsertFailed, 
	UpdateSucceed,
	UpdateFailed,
	
	ClientExist, // 存在
	ClientNotExist, // 不存在
	HotelExist,
	HotelNotExist,
	EvaluateSuccess,
	EvaluateFailed,
	
	RegisterSuccess,
	RegisterFail,
	ImportSuccess,
	ImportFailed,
	HotelAddSuccess,
	HotelAddFail,
	RoomUpdateSuccess,
	RoomUpdateFailed,
	WebAddSuccess,
	WebAddFail, 
	
	ChangeFail, 
	ChangeSuccess,
	
	IllegalInputType,
	
}
