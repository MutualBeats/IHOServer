package rmihelper;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import data.datafactory.DataFactoryMySqlImpl;
import dataservice.datafactory.DataFactory;

public class RMIHelper {
	/**
	 * Default port;
	 */
	private final static int DE_PORT = 8888;
	
	private int port;
	
	private String address;
	/**
	 *  Registry 保存, 之后可以 Unregist
	 */
	private Registry registry;
	
	private DataFactory dataFactory;
	
	private static RMIHelper helper = new RMIHelper();
	
	private RMIHelper() {
		port = DE_PORT;
		registry = null;
		address = getIPAddress();
		dataFactory = DataFactoryMySqlImpl.getInstance();
	}
	
	public static RMIHelper getInstance() {
		return helper;
	}
	
	/**
	 * Start
	 */
	public void run(){
		try {
			registry = LocateRegistry.createRegistry(port);
			Naming.rebind(dealServerName(port, address), dataFactory);
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Fail to open server");
		} 
	}
	/**
	 * Change port
	 * @param port
	 */
	public void changePort(int port) {
		if(port == this.port){
			return;
		}
		this.port = port;
		restart();
	}
	/**
	 * Restart
	 */
	public void restart() {
		off();
		//Reget ip config
		address = getIPAddress();
		run();
	}
	/**
	 * Turn off
	 */
	public void off() {
		if(registry != null){
			try {
				registry.unbind(dealServerName(port, address));
				UnicastRemoteObject.unexportObject(dataFactory, true);
				System.out.println("The server is closed");
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Fail to close server");
			}
		} else {
			System.out.println("Server is not open");
		}
	}
	
	private String dealServerName(int port, String ip) {
		return "rmi://"+ ip + ":"+port+"/"+DataFactory.class.getName();
	}
	
	private String getIPAddress() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
}
