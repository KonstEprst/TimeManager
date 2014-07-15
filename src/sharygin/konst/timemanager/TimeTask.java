package sharygin.konst.timemanager;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Debug;
import android.os.Handler;
import android.provider.Settings.System;
import android.util.Log;
import sharygin.konst.timemanager.interfaces.StatusChangeable;
import sharygin.konst.timemanager.interfaces.Taskable;
import sharygin.konst.timemanager.interfaces.ValueChangeable;
import util.TaskStatus;

public class TimeTask implements Taskable{

	List<StatusChangeable> statusListeners = new ArrayList<StatusChangeable>();
	List<ValueChangeable> valueChangeListeners = new ArrayList<ValueChangeable>();
	
	private TaskStatus status = TaskStatus.STOPPED;
	private InnerThread it;
	private long time = 0;
	
	@Override
	public void startTask() {
		setTaskStatus(TaskStatus.RUNNING);
		
		it = new InnerThread();
		
		if(Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB){
			it.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, time);
		} else {
			it.execute(time);
		}
			
		 
	}

	@Override
	public void stopTask() {
		setTaskStatus(TaskStatus.STOPPED);
		it.cancel(true);
		time = 0;
	}

	@Override
	public void pauseTask() {
		setTaskStatus(TaskStatus.PAUSED);
		it.cancel(true);
	}

	@Override
	public TaskStatus getStatus() {
		return status;
	}
	
	public Long getCurrentTime(){
		return time;
	}
	
	public void setTaskStatus(TaskStatus value){
		if(status != value){
			status = value;
			fireStatusChanged();
		}
	}
	
	private void fireValueChanged(Long value){
		for(ValueChangeable listener : valueChangeListeners){
			listener.onValueChange(value);
		}
	}
	
	public void addOnValueChangedListener(ValueChangeable listener){
		valueChangeListeners.add(listener);
	}
	
	public void removeValueChangedListener(ValueChangeable listener){
		valueChangeListeners.remove(listener);
	}
	
	
	private void fireStatusChanged() {
		for (StatusChangeable listener : statusListeners) {
			listener.onStatusChanged();
		}
	}
		
	public void addOnStatusChangedListener(StatusChangeable listener){
		statusListeners.add(listener);
	}
	
	public void removeOnStatusChangedListener(StatusChangeable listener){
		statusListeners.remove(listener);
	}
	
	class InnerThread extends AsyncTask<Long, Long, Long>{

		@Override
		protected Long doInBackground(Long... params) {
			Long t = params[0];
			
			Log.d("Attention", "Entered AsyncTask");
			
			try {
				while (!isCancelled()) {
					Thread.sleep(1000);
					publishProgress(t++);
				}
			} catch (InterruptedException ex) { 
				Log.w("error", ex.getMessage());
			}
			
			return t;
		}
		
		@Override
		protected void onProgressUpdate(Long... values) {
			time = values[0];
			fireValueChanged(values[0]);
		}
	}
}

