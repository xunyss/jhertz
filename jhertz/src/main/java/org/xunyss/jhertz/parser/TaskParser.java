package org.xunyss.jhertz.parser;

import org.xunyss.jhertz.ProcessTask;
import org.xunyss.jhertz.Task;

/**
 * 
 * @author XUNYSS
 */
public class TaskParser {

	/**
	 * 
	 * @param command
	 * @return
	 */
	public static Task parse(String sTask) {
		return new ProcessTask(sTask);
	}
}
