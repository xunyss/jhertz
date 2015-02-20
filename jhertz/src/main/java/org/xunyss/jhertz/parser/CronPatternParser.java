package org.xunyss.jhertz.parser;

import java.util.StringTokenizer;

import org.xunyss.jhertz.TimeTrigger;

/**
 * 
 * @author XUNYSS
 */
class CronPatternParser {
	
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	static TimeTrigger parse(String cronPattern) {
		TimeTrigger reserveTime = new TimeTrigger();
		
		StringTokenizer tokenizer = new StringTokenizer(cronPattern);
		while (tokenizer.hasMoreTokens()) {
			for (int pidx = 0; pidx < 6; pidx++) {
				reserveTime.set(pidx, parseToken(tokenizer.nextToken()));
			}
		}
		
		return reserveTime;
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	static int[] parseToken(String token) {
		if ("*".equals(token)) {
			return new int[0];
		}
		
		StringTokenizer tokenizer = new StringTokenizer(token, ",");
		int size = tokenizer.countTokens(), cnt = 0;
		int[] vals = new int[size];
		while (tokenizer.hasMoreElements()) {
			vals[cnt++] = Integer.parseInt(tokenizer.nextToken());
		}
		
		return vals;
	}
}
