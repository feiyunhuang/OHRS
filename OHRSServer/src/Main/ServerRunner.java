package Main;

import rmi.RMIServer;

public class ServerRunner {
	
	private ServerRunner(){
		new RMIServer();
	}

	public static void main(String args[]){
		new ServerRunner();
	}
	
}
