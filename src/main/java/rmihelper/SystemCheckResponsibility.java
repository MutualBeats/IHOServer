package rmihelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import data.datafactory.DataFactoryMySqlImpl;
import po.credit.CreditPO;
import po.order.OrderPO;
import po.user.ClientPO;
import util.Time;
import util.credit.CreditChangeAction;

public class SystemCheckResponsibility {
	
	private static OrderUpdate order;
	private static CreditUpdate credit;
	private static ClientInfo client;
	private static RoomUpdate room;

	private static Timer ORDER_REFRESH_SERVICE;

	private static boolean startOrderBackRefreshService() {
		if (ORDER_REFRESH_SERVICE != null) {
			QuickStart.sendMessage("You Must Close the Order Refresh Service");
			return false;
		}
		// 1000ms*60s*60min*24h = 86400000)
		ORDER_REFRESH_SERVICE = new Timer();
		ORDER_REFRESH_SERVICE.schedule(new OrderRefresh(), 0, 86400000);
		return true;
	}

	private static boolean endOrderBackRefreshService() {
		if (ORDER_REFRESH_SERVICE == null) {
			QuickStart.sendMessage("No Order Refresh Service Exist");
			return false;
		}
		ORDER_REFRESH_SERVICE.cancel();
		ORDER_REFRESH_SERVICE = null;
		return true;
	}

	static class OrderRefresh extends TimerTask {
		@Override
		public void run() {
			// The order refresh background service
			QuickStart.sendMessage("Refresh order service start");
			// TODO : Finish the order state refresh.
			check();
			// 超过最晚执行时间的未执行订单置为异常订单
			ArrayList<OrderPO> abnormalOrderList = order.updateOrderState();
			for (OrderPO order : abnormalOrderList) {
				// 扣除该客户信用
				ClientPO clientPO = client.getClientInfo(order.getClientID());
				CreditPO creditPO = new CreditPO();
				creditPO.setClientID(order.getClientID());
				creditPO.setOrderID(order.getOrderID());
				creditPO.setAction(CreditChangeAction.AbnormalOrder);
				creditPO.setChangeTime(Time.getCurrentDate() + " " + "00:00:00");
				creditPO.setChangeValue(-(int)order.getValue());
				creditPO.setCredit(clientPO.getCredit() - (int)order.getValue());
				credit.insertCreditRecord(creditPO);
				// 更新异常订单中房间状态为未预订，并删除房间记录
				room.updateRoomForAbnormalOrder(order);
			}
			// 入住日期为当天的房间状态改为已预订
			ArrayList<OrderPO> todayCheckInOrderList = order.findTodayCheckInOrder();
			for (OrderPO order : todayCheckInOrderList) {
				room.updateRoomForCheckInOrder(order);
			}
		}
		
		private void check() {
			try {
				if (order == null)
					order = DataFactoryMySqlImpl.getDataServiceInstance().getOrderUpdate();
				if (credit == null)
					credit = DataFactoryMySqlImpl.getDataServiceInstance().getCreditUpdate();
				if (client == null)
					client = DataFactoryMySqlImpl.getDataServiceInstance().getClientInfo();
				if (room == null)
					room = DataFactoryMySqlImpl.getDataServiceInstance().getRoomUpdate();				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean endBackRefreshService() {
		return startOrderBackRefreshService();
	}

	public static boolean startBackRefreshService() {
		return endOrderBackRefreshService();
	}


}
