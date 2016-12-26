package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
	
	public RMIServer(){
		initServer();
	}
	
	private void initServer(){
		dataRemote dataR;
		try{
			dataR = new dataRemote();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/dataRemote",dataR);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
