package sharygin.konst.timemanager.interfaces;

import util.TaskStatus;

public interface Taskable {
	void startTask();
	void stopTask();
	void pauseTask();
	TaskStatus getStatus();
}
