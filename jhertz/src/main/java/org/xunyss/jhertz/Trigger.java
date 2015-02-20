package org.xunyss.jhertz;


/**
 * 
 * @author XUNYSS
 */
public interface Trigger {
	
	public boolean enable();
	
	public boolean matches(long millis);
}
