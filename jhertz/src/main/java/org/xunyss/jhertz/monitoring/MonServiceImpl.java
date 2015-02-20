package org.xunyss.jhertz.monitoring;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MonServiceImpl extends UnicastRemoteObject implements MonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MonServer monServer = null;
	
	protected MonServiceImpl(MonServer monServer) throws RemoteException {
		super();
		this.monServer = monServer;
	}
	
	public void request(String command) throws RemoteException {
		monServer.processCommand(command);
	}
}
