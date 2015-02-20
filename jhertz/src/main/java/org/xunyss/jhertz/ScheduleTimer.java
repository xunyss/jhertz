package org.xunyss.jhertz;

import org.xunyss.jhertz.log.Log;

/**
 * 
 * @author XUNYSS
 */
/* friendly */ class ScheduleTimer extends Thread {

	/**
	 * owner scheduler
	 */
	private Scheduler scheduler = null;
	
	/**
	 * 
	 */
	private long period = 0L;
	
	/**
	 * 
	 * @param scheduler
	 */
	/* friendly */ ScheduleTimer(Scheduler scheduler, long period) {
		this.scheduler = scheduler;
		this.period = period;
		setName(scheduler.getClass().toString());
	}
	
	/**
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		long millis = System.currentTimeMillis();
		long nextMillis = 0L, sleepMillis = 0L;
		
		while (true) {
			nextMillis = ((millis / period) + 1) * period;
			sleepMillis = (nextMillis - System.currentTimeMillis());
			if (sleepMillis > 0) {
				try {
					safeSleep(sleepMillis);
				}
				// sleep 메소드가 수행중이 아닌상태에서 interrupt() 메소드가 수행됐을 경우
				// 다음번 sleep (or join) 메소드가 시작되는 순간 InterruptedException 발생 함.
				catch (InterruptedException ie) {
					Log.debug("ScheduleTimer Thread is interrupted.");
					break;
				}
			}
			
			millis = System.currentTimeMillis();
			scheduler.fireTrigger(millis);
		}
	}
	
	/**
	 * Thread.sleep() 메소드가 지정한 시간보다 적게 동작하는 것 대비.
	 * 
	 * @param millis
	 * @throws InterruptedException
	 */
	private void safeSleep(long millis) throws InterruptedException {
		long done = 0;
		do {
			long before = System.currentTimeMillis();
			sleep(millis - done);
			long after = System.currentTimeMillis();
			done += (after - before);
		}
		while (done < millis);
	}
	
	
	
	/* debug */ @Override
	protected void finalize() {
		Log.debug("SecheduleTimer::finalize() >> SecheduleTimer Object is deleted.!");
	}
}
