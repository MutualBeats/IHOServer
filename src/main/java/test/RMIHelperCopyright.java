//package test;
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
//import rmihelper.QuickStart;
//
///**
// * @see QuickStart
// * @author Saltwater
// * 
// * This is the copy of RMIHelper
// * Only Change Part of The Visibility of Some Properties
// * For the Test of the Connection of RMI Connection. 
// */
//public class RMIHelperCopyright {
//	/**
//	 * Default port;
//	 */
//	private final static int DE_PORT = 8888;
//	
//	public int port;
//	
//	public String address;
//	/**
//	 *  Registry 保存, 之后可以 Unregist
//	 */
//	public Registry registry;
//	
//	private DataFactory dataFactory;
//	
//	private static RMIHelperCopyright helper = new RMIHelperCopyright();
//	
//	private RMIHelperCopyright() {
//		port = DE_PORT;
//		registry = null;
//		address = getIPAddress();
//		dataFactory = DataFactoryMySqlImpl.getDataFactoryInstance();
//	}
//	
//	public static RMIHelperCopyright getInstance() {
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
//			}catch (RemoteException | MalformedURLException e) {
//				System.out.println("Fail to open server");
////				e.printStackTrace();
//			}
//		}else {
//			System.out.println("Server Open Already");
//		}
//	}
//	/**
//	 * Change port
//	 * @param port
//	 */
//	public void changePort(int port) {
//		off();
//		if(port == this.port){
//			return;
//		}
//		this.port = port;
//		run();
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
//				Naming.unbind(dealServerName(port, address));
//				UnicastRemoteObject.unexportObject(registry, true);
//				registry = null;
//				System.out.println("The server is closed");
//			} catch (RemoteException | MalformedURLException | NotBoundException e) {
//				System.err.println("Fail to close server");
////				e.printStackTrace();
//			}
//		} else {
//			System.out.println("Server is not open");
//		}
//	}
//	
//	public String dealServerName(int port, String ip) {
//		return "rmi://"+ ip + ":"+port+"/"+DataFactory.class.getName();
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
