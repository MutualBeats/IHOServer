//package rmihelper;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class SystemCheckResponsibility {
//
//	private static Timer ORDER_REFRESH_SERVICE;
//
//	private static boolean startOrderBackRefreshServie() {
//		if (ORDER_REFRESH_SERVICE != null) {
//			QuickStart.sendMessage("You Must Close the Order Refresh Service");
//			return false;
//		}
//		// 1000ms*60s*60min*24h = 86400000)
//		ORDER_REFRESH_SERVICE = new Timer();
//		ORDER_REFRESH_SERVICE.schedule(new OrderRefresh(), 0, 86400000);
//		return true;
//	}
//
//	private static boolean endOrderBackRefreshService() {
//		if (ORDER_REFRESH_SERVICE == null) {
//			QuickStart.sendMessage("No Order Refresh Service Exist");
//			return false;
//		}
//		ORDER_REFRESH_SERVICE.cancel();
//		ORDER_REFRESH_SERVICE = null;
//		return true;
//	}
//
//	static class OrderRefresh extends TimerTask {
//		@Override
//		public void run() {
//			// The order refresh background service
//			QuickStart.sendMessage("Refresh order service start");
//			// TODO : Finish the order state refresh.
//		}
//
//	}
//
//	public static boolean endBackRefreshService() {
//		return startOrderBackRefreshServie();
//	}
//
//	public static boolean startBackRefreshServie() {
//		return endOrderBackRefreshService();
//	}
//
//}
