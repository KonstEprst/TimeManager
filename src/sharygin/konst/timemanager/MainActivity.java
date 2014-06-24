package sharygin.konst.timemanager;

import java.util.ArrayList;
import java.util.List;

import sharygin.konst.timemanager.interfaces.Taskable;
import util.CustomAdapter;
import util.TaskStatus;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			ListView lv = (ListView)rootView.findViewById(R.id.lvTasks);
			
			List<Taskable> asd = populateList();
			
			
			CustomAdapter ca = new CustomAdapter(container.getContext(), R.layout.task_view, asd);

			lv.setAdapter(ca);
			
			return rootView;
		}
		
		private List<Taskable> populateList(){
			List<Taskable> list = new ArrayList<Taskable>();
			
			list.add(new TimeTask());
			list.add(new TimeTask());
			list.add(new TimeTask());
			
//			TimeTask tt1 = new TimeTask();
//			tt1.setTaskStatus(TaskStatus.STOPPED);
//			list.add(tt1);
//			
//			TimeTask tt2 = new TimeTask();
//			tt1.setTaskStatus(TaskStatus.STOPPED);
//			list.add(tt2);
//			
//			TimeTask tt3 = new TimeTask();
//			list.add(tt3);
			
			return list;
		}
	}

}
