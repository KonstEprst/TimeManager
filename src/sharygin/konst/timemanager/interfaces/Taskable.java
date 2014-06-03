package sharygin.konst.timemanager.interfaces;

import sharygin.konst.timemanager.TaskStatus;

public interface Taskable {
	void startTask();
	void stopTask();
	void pauseTask();
	TaskStatus getStatus();
}
