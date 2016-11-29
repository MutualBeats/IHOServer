/**
 * @author huangxiao
 * @version 2016年11月22日
 */
package util.credit;

/**
 * 信用改变动作枚举类
 */
public enum CreditChangeAction {
	ExecuteOrder, // 执行订单
	AbnormalOrder, // 异常订单
	RepealOrder, // 撤销订单
	Deposit //充值信用
}
