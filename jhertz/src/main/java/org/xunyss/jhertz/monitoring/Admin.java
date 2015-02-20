package org.xunyss.jhertz.monitoring;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Admin {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9876);
			MonService service = (MonService)registry.lookup(MonService.BIND_NAME);

			service.request(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
