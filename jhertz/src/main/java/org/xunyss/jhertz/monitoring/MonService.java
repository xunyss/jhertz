package org.xunyss.jhertz.monitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MonService extends Remote {

	public static final String BIND_NAME = "jHertz.monitoring.service";
	
	public void request(String command) throws RemoteException;
}