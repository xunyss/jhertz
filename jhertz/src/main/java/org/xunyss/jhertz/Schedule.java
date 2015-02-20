package org.xunyss.jhertz;

/**
 * 
 * @author XUNYSS
 */
public class Schedule {
	
	/**
	 * 
	 */
	private String id = null;
	
	/**
	 * 
	 */
	private Trigger trigger = null;
	/**
	 * 
	 */
	private Task task = null;
	
	public Schedule(String id, Trigger trigger, Task task) {
		this.id = id;
		
		this.trigger = trigger;
		this.task = task;
	}
	
	public Schedule(Trigger trigger, Task task) {
		this.trigger = trigger;
		this.task = task;
	}
	
	public String getId() {
		return id;
	}
	
	public Trigger getTrigger() {
		return trigger;
	}
	
	public Task getTask() {
		return task;
	}
}
