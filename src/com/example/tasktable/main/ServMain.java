package com.example.tasktable.main;

import java.util.Calendar;

import com.example.tasktable.R;
import com.example.tasktable.friday.Friday;
import com.example.tasktable.friday.FridaySQL;
import com.example.tasktable.monday.Monday;
import com.example.tasktable.monday.MondaySQL;
import com.example.tasktable.saturday.Saturday;
import com.example.tasktable.saturday.SaturdaySQL;
import com.example.tasktable.sunday.Sunday;
import com.example.tasktable.sunday.SundaySQL;
import com.example.tasktable.thursday.Thursday;
import com.example.tasktable.thursday.ThursdaySQL;
import com.example.tasktable.tuesday.Tuesday;
import com.example.tasktable.tuesday.TuesdaySQL;
import com.example.tasktable.wednesday.Wednesday;
import com.example.tasktable.wednesday.WednesdaySQL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ServMain extends Service {

	public static String filename = "Check Notification";
	private String[] values;
	String convert;
	CharSequence cs_big, cs_small;
	NotificationCompat.Builder nb;
	private NotificationManager nm;
	private Notification n;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO do something useful
		onCreate();
		return Service.START_STICKY;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SharedPreferences someData;
		someData = getSharedPreferences(filename, 0);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		switch (day) {
		case Calendar.MONDAY:
			MondaySQL mySQL1;
			mySQL1 = new MondaySQL(ServMain.this);
			mySQL1.Open();
			values = mySQL1.getData();
			Boolean check1 = someData.getBoolean("CHECK", true);
			StringBuffer buffer1 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Monday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer1.append(values[i]);
					buffer1.append(System.getProperty("line.separator"));
				}
				convert = buffer1.toString();
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
				if (check1) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}

			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.TUESDAY:
			TuesdaySQL mySQL2;
			mySQL2 = new TuesdaySQL(ServMain.this);
			mySQL2.Open();
			values = mySQL2.getData();
			Boolean check2 = someData.getBoolean("CHECK", true);
			StringBuffer buffer2 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Tuesday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer2.append(values[i]);
					buffer2.append(System.getProperty("line.separator"));
				}
				convert = buffer2.toString();
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
				if (check2) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.WEDNESDAY:
			WednesdaySQL mySQL3;
			mySQL3 = new WednesdaySQL(ServMain.this);
			mySQL3.Open();
			values = mySQL3.getData();
			Boolean check3 = someData.getBoolean("CHECK", true);
			StringBuffer buffer3 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Wednesday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer3.append(values[i]);
					buffer3.append(System.getProperty("line.separator"));
				}
				convert = buffer3.toString();
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
				if (check3) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.THURSDAY:
			ThursdaySQL mySQL4;
			mySQL4 = new ThursdaySQL(ServMain.this);
			mySQL4.Open();
			values = mySQL4.getData();
			Boolean check4 = someData.getBoolean("CHECK", true);
			StringBuffer buffer4 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Thursday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer4.append(values[i]);
					buffer4.append(System.getProperty("line.separator"));
				}
				convert = buffer4.toString();
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
				if (check4) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.FRIDAY:
			FridaySQL mySQL5;
			mySQL5 = new FridaySQL(ServMain.this);
			mySQL5.Open();
			values = mySQL5.getData();
			Boolean check5 = someData.getBoolean("CHECK", true);
			StringBuffer buffer5 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Friday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer5.append(values[i]);
					buffer5.append(System.getProperty("line.separator"));
				}
				convert = buffer5.toString();
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
				if (check5) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.SATURDAY:
			SaturdaySQL mySQL6;
			mySQL6 = new SaturdaySQL(ServMain.this);
			mySQL6.Open();
			values = mySQL6.getData();
			Boolean check6 = someData.getBoolean("CHECK", true);
			StringBuffer buffer6 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Saturday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer6.append(values[i]);
					buffer6.append(System.getProperty("line.separator"));
				}
				convert = buffer6.toString();
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
				if (check6) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		case Calendar.SUNDAY:
			SundaySQL mySQL7;
			mySQL7 = new SundaySQL(ServMain.this);
			mySQL7.Open();
			values = mySQL7.getData();
			Boolean check7 = someData.getBoolean("CHECK", true);
			StringBuffer buffer7 = new StringBuffer();
			if (values.length != 0) {
				Intent resultIntent = new Intent(this, Sunday.class);
				PendingIntent resultPendingIntent = PendingIntent.getActivity(
						this, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				for (int i = 0; i < values.length; i++) {
					buffer7.append(values[i]);
					buffer7.append(System.getProperty("line.separator"));
				}
				convert = buffer7.toString();
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
				n.flags = Notification.FLAG_ONGOING_EVENT;
				if (check7) {
					nm.notify(0, n);
				} else {
					nm.cancel(0);
				}
			} else {
				nm.cancel(0);
			}
			break;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
