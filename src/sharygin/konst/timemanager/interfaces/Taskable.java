package sharygin.konst.timemanager.interfaces;

import util.TaskStatus;

public interface Taskable {
	void startTask();
	void stopTask();
	void pauseTask();
	TaskStatus getStatus();
	
	void addOnValueChangedListener(ValueChangeable listener);
	void removeValueChangedListener(ValueChangeable listener);
	
	void addOnStatusChangedListener(StatusChangeable listener);
	void removeOnStatusChangedListener(StatusChangeable listener);
}
