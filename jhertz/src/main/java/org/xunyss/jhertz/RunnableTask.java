package org.xunyss.jhertz;

/**
 * 
 * @author XUNYSS
 */
public class RunnableTask implements Task {

	/**
	 * 
	 */
	private Runnable runnable = null;
	
	/**
	 * 
	 * @param runnable
	 */
	public RunnableTask(Runnable runnable) {
		this.runnable = runnable;
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.anzu.jhertz.Task#execute()
	 */
	@Override
	public void execute() {
		Thread t = new Thread(runnable);
		t.start();
	}
}
