package util;

import java.util.List;

import sharygin.konst.timemanager.R;
import sharygin.konst.timemanager.interfaces.Taskable;
import sharygin.konst.timemanager.interfaces.ValueChangeable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.NumberPicker.OnValueChangeListener;

public class CustomAdapter extends ArrayAdapter<Taskable> implements OnClickListener{

	List<Taskable> mList;
	Context context;
	ListView mParentListView;
	
	public CustomAdapter(Context context, int resource, List<Taskable> objects) {
		super(context, resource, objects);
		
		mList = objects;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		mParentListView = (ListView)parent;
		
		View view = convertView;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.task_view, null);
		}

		Taskable item = getItem(position);
		
		ImageButton im = (ImageButton)view.findViewById(R.id.ibStartPause);
		im.setOnClickListener(this);
		switch(item.getStatus()){
		case PAUSED:
			im.setImageResource(android.R.drawable.ic_media_play);
			break;
			
		case RUNNING:
			im.setImageResource(android.R.drawable.ic_media_pause);
			break;
			
		case STOPPED:
			im.setImageResource(android.R.drawable.ic_media_play);
			break;
		}

		im = (ImageButton)view.findViewById(R.id.ibStop);
		im.setOnClickListener(this);
		
		return view;
	}
	
	
	
	@Override
	public int getCount() {
		return mList.size();
	}
	
	@Override
	public Taskable getItem(int position) {
		return mList.get(position);
	}
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ibStop:
			StopClicked(v);
			break;
			
		case R.id.ibStartPause:
			PlayPauseClicked(v);
			break;
		}
		
	}
	
	private void PlayPauseClicked(View v) {
		final int pos = mParentListView.getPositionForView(v);
		Taskable item = getItem(pos);
		
		
		switch (item.getStatus()) {
		case PAUSED:
		case STOPPED:
			item.startTask();
			((ImageButton) v)
					.setImageResource(android.R.drawable.ic_media_pause);
			
			item.addOnValueChangedListener(new ValueChangeable() {
				
				@Override
				public void onValueChange(long newValue) {
					TextView tv = (TextView)mParentListView.getChildAt(pos).findViewById(R.id.timerText);
					long hours = newValue / 60 / 60;
					long minutes = newValue / 60;
					long seconds = newValue % 60;
					
					tv.setText(hours + ":" + minutes + ":" + seconds);
					
				}
			});
			
			break;

		case RUNNING:
			
			item.pauseTask();
			((ImageButton) v)
					.setImageResource(android.R.drawable.ic_media_play);
			break;

		}

		v.invalidate();
		v.requestLayout();
	}
	
	private void StopClicked(View v){
		Toast.makeText(context, "Нажата кнопка stop", Toast.LENGTH_SHORT).show();
	}
}
