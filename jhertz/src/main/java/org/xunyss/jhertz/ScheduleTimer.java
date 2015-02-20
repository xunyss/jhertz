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
				// sleep �޼ҵ尡 �������� �ƴѻ��¿��� interrupt() �޼ҵ尡 ������� ���
				// ������ sleep (or join) �޼ҵ尡 ���۵Ǵ� ���� InterruptedException �߻� ��.
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
	 * Thread.sleep() �޼ҵ尡 ������ �ð����� ���� �����ϴ� �� ���.
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
