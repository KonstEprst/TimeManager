package util;

public enum TaskStatus {
	RUNNING(0)
	, STOPPED(1)
	, PAUSED(2);
	
	private final int mStatus;
	
	private TaskStatus(int value){
		mStatus = value;
	}
	
	public int getIntValue(){
		return mStatus;
	}
}
