package org.xunyss.jhertz;

/**
 * 
 * @author XUNYSS
 */
public class ProcessTask implements Task {

	/**
	 * 
	 */
	private String command = null;
	
	/**
	 * 
	 * @param command
	 */
	public ProcessTask(String command) {
		this.command = command;
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.anzu.jhertz.Task#execute()
	 */
	@Override
	public void execute() {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.getInputStream().close();
			process.waitFor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
