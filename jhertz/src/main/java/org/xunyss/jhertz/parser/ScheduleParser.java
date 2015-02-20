package org.xunyss.jhertz.parser;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.xunyss.jhertz.Schedule;

/**
 * 
 * @author XUNYSS
 */
public class ScheduleParser {
	
	/**
	 * 
	 * @param schedule
	 * @return
	 */
	public static Schedule parse(String sSchedule) {
		String scheduleId = "", sTrigger = "", sTask = "";
		
		boolean bScheduleId = hasScheduleId(sSchedule);
		boolean bRelationTrigger = TriggerParser.hasRelationTrigger(sSchedule);
		
		StringTokenizer tokenizer = new StringTokenizer(sSchedule);
		
		if (bScheduleId) {
			scheduleId = tokenizer.nextToken();
		}
		
		if (bRelationTrigger) {
			sTrigger = tokenizer.nextToken();
		}
		else {
			for (int i = 0; i < 6; i++) {
				sTrigger += tokenizer.nextToken() + " ";
			}
		}
		
		while (tokenizer.hasMoreTokens()) {
			sTask += tokenizer.nextToken() + " ";
		}
		
		return new Schedule(scheduleId, TriggerParser.parse(sTrigger), TaskParser.parse(sTask));
	}
	
	public static boolean hasScheduleId(String sSchedule) {
		return isScheduleId(new StringTokenizer(sSchedule).nextToken());
	}
	
	public static boolean isScheduleId(String token) {
		/*
		 * pattern : [schedule_id]
		 */
		return Pattern.matches("\\x5B\\w*\\x5D", token);
	}
}
