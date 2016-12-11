//package rmihelper;
//
//import java.net.InetAddress;
//import java.net.MalformedURLException;
//import java.net.UnknownHostException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//
//import data.datafactory.DataFactoryMySqlImpl;
//import dataservice.datafactoryservice.DataFactory;
//
//public class RMIHelper {
//	
//	private final int port = 8888;
//	
//	private String address;
//	/**
//	 *  Registry 保存, 之后可以 Unregist
//	 */
//	private Registry registry;
//	
//	private DataFactory dataFactory;
//	
//	private static RMIHelper helper = new RMIHelper();
//	
//	private RMIHelper() {
//		registry = null;
//		address = getIPAddress();
////		address = "192.168.99.255";
//		dataFactory = DataFactoryMySqlImpl.getDataFactoryInstance();
//	}
//	
//	
//	public static RMIHelper getInstance() {
//		return helper;
//	}
//	
//	/**
//	 * Start
//	 */
//	public void run(){
//		if(registry == null) {
//			try {
//				registry = LocateRegistry.createRegistry(port);
//				Naming.rebind(dealServerName(port, address), dataFactory);
//				//后台更新服务开启
//				if(SystemCheckResponsibility.startBackRefreshServie()) {
//					return;
//				}
//				QuickStart.sendMessage("Server Open Successfully\r\n"
//						+ "IP : " + address + "\r\n"
//						+ "Port : " + port + "\r\n");
//			}catch (RemoteException | MalformedURLException e) {
////				e.printStackTrace();
//				QuickStart.sendMessage("Fail to Open the Server ! Please Check Any Other Server Run Now");
//			}
//			
//			
//		}else {
//			QuickStart.sendMessage("Server Open Already");
//		}
//	}
//	/**
//	 * Restart
//	 */
//	public void restart() {
//		off();
//		//Reget ip config
//		address = getIPAddress();
//		run();
//	}
//	/**
//	 * Turn off
//	 */
//	public void off() {
//		if(registry != null){
//			try {
//				//服务关闭
//				if(SystemCheckResponsibility.endBackRefreshService()) {
//					return;
//				}
//				Naming.unbind(dealServerName(port, address));
//				//Notice : the object to unexport is Registry .
//				UnicastRemoteObject.unexportObject(registry, true);
//				registry = null;
//				QuickStart.sendMessage("Server Close Successfully");
//			} catch (RemoteException | MalformedURLException | NotBoundException e) {
//				System.err.println("Fail to close server");
////				e.printStackTrace();
//				QuickStart.sendMessage("Fail to close server");
//			}
//		} else {
//			QuickStart.sendMessage("No Server Found");
//		}
//		
//	}
//	
//	private String dealServerName(int port, String ip) {
//		return "rmi://"+ ip + ":"+port+"/DataFactory";
//	}
//	
//	private String getIPAddress() {
//		String ip = null;
//		try {
//			ip = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		return ip;
//	}
//	
//}
