//package dataservice.orderdataservice;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//import po.order.OrderPO;
//import util.MemberType;
//import util.OrderState;
//import util.RoomType;
//
//public class OrderDataService_Driver {
//	
//	public void drive(OrderDataService orderDataService) {
//		OrderPO po1 = new OrderPO("1234567890123456", "1234567890", "WoDeMa", "12345678901", MemberType.Ordinary, OrderState.UnExcuted, "12345678", "和园宾馆", "3B346", RoomType.Single, 1, 10000 ,1000, "2016/10/14", "2016/10/15", "2016/10/16", "2016/10/14", "2016/10/16", "2016/10/15","2015/10/15",1,false);
//		OrderPO po2 = new OrderPO("1234567890123457", "1234567891", "WoDeTian", "98765432125", MemberType.Enterprise, OrderState.Canceled, "12345678", "和园宾馆", "3B347",RoomType.Double, 2, 10000 ,-1000, "2016/10/14", "", "", "2016/10/14", "2016/10/16", "2016/10/14", "2016/10/15",4,false);
//		OrderPO po3 = new OrderPO("1234567890123458", "1234567891", "WoDeTian", "98765432125", MemberType.Enterprise, OrderState.Canceled, "12345678", "和园宾馆", "3B347",RoomType.Double, 1, 10000 ,-1000, "2016/10/17", "", "", "2016/10/17", "2016/10/19", "2016/10/17","2016/10/18",1,false);
//
//		try {
//			orderDataService.insert(po1);
//			orderDataService.insert(po2);
//			orderDataService.insert(po3);
//			po2.setCreditChange(500);
//			orderDataService.update(po2);
//			OrderPO po;
//			po = orderDataService.findById("1234567890123456");
//			//以下为按 orderId 查找的结果
//			System.out.println(po.getUserName() + " " + po.getHotelName());
//			
//			ArrayList<OrderPO> po4;
//			po4 = orderDataService.findByRoom("12345678901", "3B346");
//			//以下为按 room查找的结果
//			po = po4.get(0);
//			System.out.println("Size : " + po4.size() + " Room Type : " + po.getRoomType().toString() + " User" + po.getUserName());
//			
//			
//			po4 = orderDataService.findByUser("1234567891");
//			//以下为按 user 查找的结果
//			po = po4.get(1);
//			System.out.println("Size : " + po4.size() + " Room Type : " + po.getRoomType().toString() + " User" + po.getUserName());
//			
//			po4 = orderDataService.findUnexcutedOrder();
//			//以下为查询未执行订单的结果
//			po = po4.get(0);
//			System.out.println("Size : " + po4.size() + " Room Type : " + po.getRoomType().toString() + " User" + po.getUserName());
//			
//			po4 = orderDataService.findHotelOrder("12345678");
//			//以下为酒店工作人员查询所在酒店所有订单的结果
//			System.out.println("Num: " + po4.size());
//			
//			po4 = orderDataService.findUOByHotel("12345678", "1234567891");
//			//以下为用户查询自己在某酒店的订单
//			System.out.println("Num: " + po4.size());
//			
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
