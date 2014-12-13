package com.example.tasktable.main;

import java.util.Calendar;

import com.example.tasktable.main.ServMain;
import com.example.tasktable.friday.Friday;
import com.example.tasktable.monday.Monday;
import com.example.tasktable.saturday.Saturday;
import com.example.tasktable.sunday.Sunday;
import com.example.tasktable.thursday.Thursday;
import com.example.tasktable.tuesday.Tuesday;
import com.example.tasktable.wednesday.Wednesday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity {

	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent i = new Intent(Main.this, ServMain.class);
		Main.this.startService(i);
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.MONDAY:
			i = new Intent(Main.this, Monday.class);
			startActivity(i);
			break;
		case Calendar.TUESDAY:
			i = new Intent(Main.this, Tuesday.class);
			startActivity(i);
			break;
		case Calendar.WEDNESDAY:
			i = new Intent(Main.this, Wednesday.class);
			startActivity(i);
			break;
		case Calendar.THURSDAY:
			i = new Intent(Main.this, Thursday.class);
			startActivity(i);
			break;
		case Calendar.FRIDAY:
			i = new Intent(Main.this, Friday.class);
			startActivity(i);
			break;
		case Calendar.SATURDAY:
			i = new Intent(Main.this, Saturday.class);
			startActivity(i);
			break;
		case Calendar.SUNDAY:
			i = new Intent(Main.this, Sunday.class);
			startActivity(i);
			break;
		}

	}
}
