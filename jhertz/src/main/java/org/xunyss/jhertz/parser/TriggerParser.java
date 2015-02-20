package org.xunyss.jhertz.parser;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.xunyss.jhertz.RelationTrigger;
import org.xunyss.jhertz.TimeTrigger;
import org.xunyss.jhertz.Trigger;

/**
 * 
 * @author XUNYSS
 */
public class TriggerParser {

	/**
	 * 
	 * @param sTrigger
	 * @return
	 */
	public static Trigger parse(String sTrigger) {
		if (isRelationTrigger(sTrigger)) {
			return parseRelationTrigger(sTrigger);
		}
		else {
			return parseTimeTrigger(sTrigger);
		}
	}
	
	public static boolean hasRelationTrigger(String sSchedule) {
		StringTokenizer tokenizer = new StringTokenizer(sSchedule);
		if (ScheduleParser.hasScheduleId(sSchedule)) {
			tokenizer.nextToken();
		}
		return isRelationTrigger(tokenizer.nextToken());
	}
	
	public static boolean isRelationTrigger(String token) {
		/*
		 * pattern : {this}[<>]{schedule_id}, {schedule_id}[<>]{this}
		 */
		return Pattern.matches("\\x7B\\w*\\x7D[<>]\\x7B\\w*\\x7D", token);
	}
	
	public static RelationTrigger parseRelationTrigger(String relationPattern) {
		char delim = relationPattern.indexOf('<') > 0 ? '<' : '>';
		
		RelationTrigger relationTrigger = new RelationTrigger();
		relationTrigger.setRelation(delim == '<' ? RelationTrigger.PREV : RelationTrigger.NEXT);
		
		
		StringTokenizer tokenizer = new StringTokenizer(relationPattern, " " + delim);
		while (tokenizer.hasMoreTokens()) {
			// more more more
		}
		
		return relationTrigger;
	}
	
	public static TimeTrigger parseTimeTrigger(String timePattern) {
		return CronPatternParser.parse(timePattern);
	}
}
