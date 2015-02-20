package org.xunyss.jhertz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xunyss.jhertz.log.Log;
import org.xunyss.jhertz.parser.ScheduleParser;

/**
 * 
 * @author XUNYSS
 */
public class Scheduler {

	/**
	 * 
	 */
	private ScheduleTimer timer = null;
	
	/**
	 * 
	 */
	private long period = 1000;
	
	/**
	 * 
	 */
	private boolean daemon = false;
	
	/**
	 * 
	 */
	private boolean running = false;
	
	/**
	 * 
	 */
	private List<Schedule> schedules = new ArrayList<Schedule>();
	
	
	public Scheduler() {
		
	}
	
	public void setDaemon(boolean daemon) {
		if (running) {
			throw new RuntimeException("setDaemon");
		}
		this.daemon = daemon;
	}
	
	
	public void schedule(Trigger trigger, Task task) {
		schedule(new Schedule(trigger, task));
	}
	
	public void schedule(Schedule schedule) {
		schedules.add(schedule);
	}
	
	public void schedule(String line) {
		schedule(ScheduleParser.parse(line));
	}
	
	public void scheduleFile(String file) {
		scheduleFile(new File(file));
	}
	
	public void scheduleFile(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				schedule(line);
			}
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try { br.close(); } catch (IOException ioe) {}
		}
	}
	
	public void start() {
		if (running) {
			Log.debug("jHertz is aleady started.");
			return;
		}
		
		timer = new ScheduleTimer(this, period);
		timer.setDaemon(daemon);
		timer.start();
		
		running = true;
	}
	
	public void stop() {
		if (!running) {
			Log.debug("jHertz is not running.");
			return;
		}
		
		timer.interrupt();	/* interrupt schedule-timer thread */
		timer = null;		/* clear schedule-timer object reference */
		
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	
	/**
	 * 
	 * 
	 * @param millis
	 */
	/* friendly */ void fireTrigger(long millis) {
		int size = schedules.size();
		Schedule schedule = null;
		
		for (int i = 0; i < size; i++) {
			schedule = schedules.get(i);
			if (!schedule.getTrigger().matches(millis)) {
				continue;
			}
			
			schedule.getTask().execute();
		}
	}
	
	
	
	@Override
	protected void finalize() {
		Log.debug("Secheduler::finalize() >> Secheduler Object is deleted.!");
	}
}
