package rmi_test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Test;

import dataservice.datafactoryservice.DataFactory;
import test.RMIHelperCopyright;

public class RMIConnectionTest {

	private RMIHelperCopyright helper = RMIHelperCopyright.getInstance();
	
	@Test
	public void runTest(){
		assertNotNull(helper);
		helper.run();
		DataFactory dataFactory = null;
		try {
			dataFactory = (DataFactory) Naming.lookup(helper.dealServerName(helper.port, helper.address));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			assert e.getClass() == NotBoundException.class : "Not Bound";
			assert e.getClass() == RemoteException.class : "Please Check You Port, and Whether Remote is Extend";
			assert e.getClass() == MalformedURLException.class : "URL ERROR";
			fail("Fail Test");
		}
		assertNotNull(dataFactory);
//		offTest();
	}
	
	@Test
	public void offTest() {
		helper.off();
		DataFactory dataFactory = null;
		try {
			dataFactory = (DataFactory) Naming.lookup(helper.dealServerName(helper.port, helper.address));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
		}
		if(dataFactory != null){
			fail("Server Not Close");
		}
		
	}
	
	@Test
	public void restartTest() {
		offTest();
		runTest();
		offTest();
	}
	
	@Test
	public void changeTest() {
		offTest();
		runTest();
		int before = helper.port;
		helper.changePort(before + 1);
		DataFactory dataFactory = null;
		try {
			dataFactory = (DataFactory) Naming.lookup(helper.dealServerName(before, helper.address));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
		}
		if(dataFactory != null){
			fail("Port Not Change");
		}
		offTest();
	}
	
}
