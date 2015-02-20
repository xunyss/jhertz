package org.xunyss.jhertz;

/**
 * 
 * @author XUNYSS
 */
public class RelationTrigger implements Trigger {

	public static final int PREV = 0;		// "<"
	public static final int NEXT = 1;		// ">"
	
	private int relationType = 0;
	
	private Schedule mainSchedule = null;
	
	private Schedule targetSchedule = null;
	
	public void setRelation(int relationType) {
		this.relationType = relationType;
	}
	
	public void setRelation(Schedule mainSchedule, Schedule targetSchedule) {
		this.mainSchedule	= mainSchedule;
		this.targetSchedule	= targetSchedule;
	}
	
	@Override
	public boolean enable() {
		return true;
	}

	@Override
	public boolean matches(long millis) {
		return false;
	}
}
