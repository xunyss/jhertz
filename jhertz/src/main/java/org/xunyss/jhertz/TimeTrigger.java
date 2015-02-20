package org.xunyss.jhertz;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.xunyss.jhertz.parser.TriggerParser;

/**
 * 
 * @author XUNYSS
 */
public class TimeTrigger implements Trigger {

	public static final int SECOND	= 0;
	public static final int MINUTE	= 1;
	public static final int HOUR	= 2;
	public static final int DAY		= 3;
	public static final int MONTH	= 4;
	public static final int WEEK	= 5;
	
	private int[][] timeParts = new int[6][];
	
	public TimeTrigger() {
		
	}
	
	public TimeTrigger(String timePattern) {
		set(timePattern);
	}
	
	public void set(String timePattern) {
		timeParts = TriggerParser.parseTimeTrigger(timePattern).timeParts;
	}
	
	public void set(int[][] partsValues) {
		timeParts = partsValues;
	}
	
	public void set(int partIndex, int[] vals) {
		timeParts[partIndex] = vals;
	}
	
	public int[][] get() {
		return timeParts;
	}
	
	public int[] get(int partIndex) {
		return timeParts[partIndex];
	}
	
	@Override
	public boolean enable() {
		return true;
	}
	
	@Override
	public boolean matches(long millis) {
		Calendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(millis);
		
		return	matches(timeParts[0], gregorianCalendar.get(Calendar.SECOND))		&&
				matches(timeParts[1], gregorianCalendar.get(Calendar.MINUTE))		&&
				matches(timeParts[2], gregorianCalendar.get(Calendar.HOUR_OF_DAY))	&&
				matches(timeParts[3], gregorianCalendar.get(Calendar.DAY_OF_MONTH))	&&
				matches(timeParts[4], gregorianCalendar.get(Calendar.MONTH))		&&
				matches(timeParts[5], gregorianCalendar.get(Calendar.DAY_OF_WEEK))	;
	}
	
	private boolean matches(int[] vals, int val) {
		if (vals == null) {
			return false;
		}
		
		if (vals.length == 0) {
			return true;
		}
		for (int in : vals) {
			if (in == val) {
				return true;
			}
		}
		
		return false;
	}
}
