package org.xunyss.jhertz.monitoring;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.xunyss.jhertz.Scheduler;

/**
 * 
 * @author XUNYSS
 */
public class MonServer {

	private Scheduler scheduler = null;
	
	private int port = 0;
	
	private Registry registry = null;
	private MonService service = null;
	
	public MonServer(Scheduler scheduler, int port) {
		this.scheduler = scheduler;
		this.port = port;
	}
	
	public void start() {
		try {
			service = new MonServiceImpl(this);
			
			registry = LocateRegistry.createRegistry(port);
			registry.bind(MonService.BIND_NAME, service);
		}
		catch (AlreadyBoundException abe) {
			abe.printStackTrace();
		}
		catch (RemoteException re) {
			re.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			registry.unbind(MonService.BIND_NAME);
			UnicastRemoteObject.unexportObject(service, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void processCommand(String command) {
		if ("stop".equals(command)) {
			
			scheduler.stop();
			scheduler = null;
			
			stop();
			System.gc();
		}
		else if ("list".equals(command)) {

		}
		else if ("stat".equals(command)) {
			
		}
	}
}
