package com.example.tasktable.wednesday;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.android.swipedismiss.SwipeDismissListViewTouchListener;
import com.example.tasktable.R;
import com.example.tasktable.friday.Friday;
import com.example.tasktable.main.Receiver;
import com.example.tasktable.monday.Monday;
import com.example.tasktable.saturday.Saturday;
import com.example.tasktable.sunday.Sunday;
import com.example.tasktable.thursday.Thursday;
import com.example.tasktable.tuesday.Tuesday;
import com.example.tasktable.wednesday.TaskAdapter;
import com.example.tasktable.wednesday.TaskAdapter1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("NewApi")
public class Wednesday extends Activity implements MultiChoiceModeListener,
		OnItemClickListener {

	private String[] values;
	private String[] menu = { "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday", "Sunday" };
	private ListView content;
	private ListView drawerlist;
	private DrawerLayout drawerlayout;
	private ActionBarDrawerToggle drawertoggle;
	SparseBooleanArray checked;

	public static String filename = "Check Notification";
	SharedPreferences someData;
	SharedPreferences.Editor editor;
	private NotificationManager nm;
	private Notification n;
	WednesdaySQL mySQL;
	String convert;
	CharSequence cs_big, cs_small;
	NotificationCompat.Builder nb;

	int c;
	private PendingIntent pendingIntent;

	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		
		c = 0;

		// Service
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Intent intent = new Intent(Wednesday.this, Receiver.class);
		pendingIntent = PendingIntent
				.getBroadcast(Wednesday.this, 0, intent, 0);
		AlarmManager alarManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),
				pendingIntent);
		setContentView(R.layout.drawer);

		someData = getSharedPreferences(filename, 0);
		content = (ListView) findViewById(R.id.content_frame);
		drawerlist = (ListView) findViewById(R.id.left_drawer);
		drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		TaskAdapter1 adapter_drawer = new TaskAdapter1(this, menu);
		drawerlist.setAdapter(adapter_drawer);

		mySQL = new WednesdaySQL(Wednesday.this);
		mySQL.Open();
		values = mySQL.getData();
		// mySQL.Close();
		final TaskAdapter adapter = new TaskAdapter(this, values);
		Notification();
		content.setEmptyView(findViewById(android.R.id.empty));
		content.setAdapter(adapter);
		Button b = (Button) findViewById(android.R.id.empty);
		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Wednesday.this, WednesdayActivity.class);
				startActivity(i);
			}
		});

		drawerlist.setOnItemClickListener(this);
		content.setOnItemClickListener(this);
		content.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		content.setMultiChoiceModeListener(this);
		getActionBar().setTitle("Wednesday");

		// Navigation-UP-Drawer
		drawertoggle = new ActionBarDrawerToggle(this, drawerlayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle("Wednesday");				
				invalidateOptionsMenu();
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(R.string.app_name);				
				invalidateOptionsMenu();
			}
		};
		getActionBar().setDisplayHomeAsUpEnabled(true);
		drawerlayout.setDrawerListener(drawertoggle);
		drawerlayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		drawerlayout.setFocusableInTouchMode(false);

		// Swipe-to-Dismiss
		SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(
				content,
				new SwipeDismissListViewTouchListener.DismissCallbacks() {

					@Override
					public boolean canDismiss(int position) {
						// TODO Auto-generated method stub
						return true;
					}

					@Override
					public void onDismiss(ListView listView,
							int[] reverseSortedPositions) {
						// TODO Auto-generated method stub
						final WednesdaySQL mySQL_del = new WednesdaySQL(
								Wednesday.this);
						mySQL_del.Open();
						String[] values_before = mySQL_del.getData();
						for (int position : reverseSortedPositions) {

							mySQL_del.DeleteEntry(values_before[position],
									mySQL_del.getID(position));

						}
						String[] values_after = mySQL_del.getData();
						mySQL_del.Close();
						onCreate(new Bundle());
						if (values_after.length == 0) {
							nm.cancel(0);
						}
					}
				});
		content.setOnTouchListener(touchListener);
	}

	public void Notification() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.WEDNESDAY) {
			Boolean check = someData.getBoolean("CHECK", true);

			StringBuffer buffer = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Wednesday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer.append(values[i]);
					buffer.append(System.getProperty("line.separator"));
				}
				convert = buffer.toString();
				cs_big = convert;
				cs_small = values[0];
				nb = new NotificationCompat.Builder(this)
						.setContentText(cs_small)
						.setSmallIcon(R.drawable.ic_launchernew)
						.setContentTitle("Task to do")
						.setStyle(
								new NotificationCompat.BigTextStyle()
										.bigText(cs_big));
				nb.setContentIntent(resultPendingIntent);
				n = nb.build();
				n.priority = 1;
				;
				n.flags = Notification.FLAG_ONGOING_EVENT;
				if (check) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
		}

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawertoggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawertoggle.syncState();
	}

	// Creating-Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Preparing-Menu
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean check = someData.getBoolean("CHECK", true);
		MenuItem check_NOT = menu.getItem(1);
		check_NOT.setChecked(check);

		boolean drawerOpen = drawerlayout.isDrawerOpen(drawerlist);
		menu.findItem(R.id.action_refresh).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	// Selection-of-Menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_refresh:
			Intent i = new Intent(Wednesday.this, WednesdayActivity.class);
			startActivity(i);
			break;
		case R.id.action_settings:
			mySQL.DeleteAll();
			onCreate(new Bundle());
			break;
		case R.id.notification:
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			Intent not_Intent;
			if (item.isChecked()) {
				item.setChecked(false);
				editor = someData.edit();
				editor.putBoolean("CHECK", false);
				editor.commit();
				nm.cancel(0);
			} else {
				item.setChecked(true);
				editor = someData.edit();
				editor.putBoolean("CHECK", true);
				editor.commit();
				if ((values.length != 0) && day == Calendar.WEDNESDAY) {
					nm.notify(0, n);
				} else if (day == Calendar.THURSDAY) {
					not_Intent = new Intent(Wednesday.this, Thursday.class);
					startActivity(not_Intent);
				} else if (day == Calendar.SUNDAY) {
					not_Intent = new Intent(Wednesday.this, Sunday.class);
					startActivity(not_Intent);
				} else if (day == Calendar.FRIDAY) {
					not_Intent = new Intent(Wednesday.this, Friday.class);
					startActivity(not_Intent);
				} else if (day == Calendar.TUESDAY) {
					not_Intent = new Intent(Wednesday.this, Tuesday.class);
					startActivity(not_Intent);
				} else if (day == Calendar.SATURDAY) {
					not_Intent = new Intent(Wednesday.this, Saturday.class);
					startActivity(not_Intent);
				} else if (day == Calendar.MONDAY) {
					not_Intent = new Intent(Wednesday.this, Monday.class);
					startActivity(not_Intent);
				}

				break;
			}
		}
		if (drawertoggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);

	}

	// Click_ITEM
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.content_frame:
			WednesdaySQL mySQL = new WednesdaySQL(Wednesday.this);
			mySQL.Open();
			String basket = mySQL.getPosition_data(position);
			mySQL.Close();
			Bundle bd = new Bundle();
			bd.putString("BASKET", basket);
			bd.putInt("BASKET_KEY", position);
			Intent i = new Intent(Wednesday.this, WednesdayActivityEdit.class);
			i.putExtras(bd);
			startActivity(i);
			break;
		case R.id.left_drawer:
			parent.getChildAt(position).setSelected(true);
			String cheese = menu[position];
			Class<?> ourClass = null;
			try {
				if (position == 0) {
					ourClass = Class.forName("com.example.tasktable.monday."
							+ cheese);
				} else if (position == 1) {
					ourClass = Class.forName("com.example.tasktable.tuesday."
							+ cheese);
				} else if (position == 2) {
					ourClass = Class.forName("com.example.tasktable.wednesday."
							+ cheese);

				} else if (position == 3) {
					ourClass = Class.forName("com.example.tasktable.thursday."
							+ cheese);
				} else if (position == 4) {
					ourClass = Class.forName("com.example.tasktable.friday."
							+ cheese);
				} else if (position == 5) {
					ourClass = Class.forName("com.example.tasktable.saturday."
							+ cheese);
				} else if (position == 6) {
					ourClass = Class.forName("com.example.tasktable.sunday."
							+ cheese);
				}
				Intent ourIntent = new Intent(Wednesday.this, ourClass);
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.show:
			SparseBooleanArray checked = content.getCheckedItemPositions();
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for (int i = 0; i < checked.size(); i++) {
				if (checked.valueAt(i)) {
					positions.add(checked.keyAt(i));
				}
			}
			int[] arr = new int[positions.size()];
			int[] arr1 = new int[positions.size()];
			int k = 0;
			for (int position : positions) {
				arr[k] = mySQL.getID(position);
				arr1[k] = position;
				k++;
			}
			for (int j = 0; j < arr.length; j++) {

				mySQL.DeleteEntry(values[arr1[j]], arr[j]);
			}
			onCreate(new Bundle());
			mode.finish();
			return true;

		}
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemCheckedStateChanged(ActionMode mode, int position,
			long id, boolean checked) {
		// TODO Auto-generated method stub
		final int checkedCount = content.getCheckedItemCount();
		switch (checkedCount) {
		case 0:
			mode.setSubtitle(null);
			break;
		case 1:
			mode.setSubtitle("One item selected");

			break;
		default:
			mode.setSubtitle("" + checkedCount);
			break;
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (c == 0) {
			drawerlayout.openDrawer(drawerlist);
			c++;
		} else {
			super.onBackPressed();
		}

	}

}