/**
 * @author huangxiao
 * @version 2016年12月14日
 */
package rmihelper;

import java.util.ArrayList;

import po.order.OrderPO;

public interface OrderUpdate {

	/**
	 * 更新订单状态（未执行置为异常）
	 */
	public ArrayList<OrderPO> updateOrderState();
	/**
	 * 获取当天入住的订单列表
	 */
	public ArrayList<OrderPO> findTodayCheckInOrder();
}
