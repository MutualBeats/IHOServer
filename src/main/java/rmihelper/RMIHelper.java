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
import dataservice.datafactoryservice.DataFactory;

public class RMIHelper {
	
	private final int port = 8888;
	
	private String address;
	/**
	 *  Registry 保存, 之后可以 Unregist
	 */
	private Registry registry;
	
	private DataFactory dataFactory;
	
	private static RMIHelper helper = new RMIHelper();
	
	private RMIHelper() {
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
		if(registry == null) {
			try {
				registry = LocateRegistry.createRegistry(port);
				Naming.rebind(dealServerName(port, address), dataFactory);
			}catch (RemoteException | MalformedURLException e) {
				System.out.println("Fail to open server");
//				e.printStackTrace();
			}
		}else {
			System.out.println("Server Open Already");
		}
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
				Naming.unbind(dealServerName(port, address));
				//Notice : the object to unexport is Registry .
				UnicastRemoteObject.unexportObject(registry, true);
				registry = null;
				System.out.println("The server is closed");
			} catch (RemoteException | MalformedURLException | NotBoundException e) {
				System.err.println("Fail to close server");
//				e.printStackTrace();
			}
		} else {
			System.out.println("Server is not open");
		}
	}
	
	private String dealServerName(int port, String ip) {
		return "rmi://"+ ip + ":"+port+"/DataFactory";
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
