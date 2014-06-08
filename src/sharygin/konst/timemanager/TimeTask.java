package sharygin.konst.timemanager;

import java.util.ArrayList;
import java.util.List;

import sharygin.konst.timemanager.interfaces.StatusChangeable;
import sharygin.konst.timemanager.interfaces.Taskable;
import util.TaskStatus;

public class TimeTask implements Taskable{

	List<StatusChangeable> statusListeners = new ArrayList<StatusChangeable>();
	
	private TaskStatus status = TaskStatus.STOPPED;
	
	@Override
	public void startTask() {
		setTaskStatus(TaskStatus.RUNNING);
	}

	@Override
	public void stopTask() {
		setTaskStatus(TaskStatus.STOPPED);
	}

	@Override
	public void pauseTask() {
		setTaskStatus(TaskStatus.PAUSED);
		
	}

	@Override
	public TaskStatus getStatus() {
		return status;
	}
	
	public void setTaskStatus(TaskStatus value){
		if(status != value){
			status = value;
			fireStatusChanged();
		}
	}
	
	private void fireStatusChanged(){
		for(StatusChangeable listener : statusListeners)
			listener.onStatusChanged();
	}
	
	public void addOnStatusChangedListener(StatusChangeable listener){
		statusListeners.add(listener);
	}
	
	public void removeOnStatusChangedListener(StatusChangeable listener){
		statusListeners.remove(listener);
	}

}