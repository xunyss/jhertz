package org.xunyss.jhertz;

import org.xunyss.jhertz.log.Log;
import org.xunyss.jhertz.monitoring.MonServer;
import org.xunyss.jhertz.test.TestTask;

public class JHertz {
	
	public static void main(String[] args) {
		Log.debug("jHertz starting...");
		
		
		// standalone
//		Scheduler scheduler = new Scheduler();
//		scheduler.setDaemon(false);
//		scheduler.start();
		
		// utility
		// no single-tone
		Scheduler scheduler = new Scheduler();
	//	scheduler.setDaemon(true);
	//	scheduler.schedule(" 0,10,20,30,40,50  *  *  *  *  * ", new TestTask(1));
		
		
		
		
		
		Trigger trigger = new TimeTrigger("0,10,20,30,40,50 * * * * *");
		Task task = new RunnableTask(new TestTask(55));
		Schedule schedule = new Schedule("1", trigger, task);
		scheduler.schedule(schedule);
		
		
		
		
		
	//	scheduler.schedule("5,15,25,35,45,55 * * * * *", "D:\\test.cmd");
		scheduler.scheduleFile("D://jhertztest.jhz");
		
		
		
		
	//	scheduler.schedule("");
		scheduler.start();
		
		System.out.println("scheduler is started.");
		
		new MonServer(scheduler, 9876).start();
		System.out.println("monitorService is started.");


scheduler = null;

		try {
			Thread.sleep(500000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
