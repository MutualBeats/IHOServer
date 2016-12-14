/**
 * @author huangxiao
 * @version 2016年12月14日
 */
package rmihelper;

import po.order.OrderPO;

public interface RoomUpdate {
	
	public void updateRoomForAbnormalOrder(OrderPO abnormalOrder);
	
	public void updateRoomForCheckInOrder(OrderPO todayCheckInOrder);

}
