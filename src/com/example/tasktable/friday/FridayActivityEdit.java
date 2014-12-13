package com.example.tasktable.friday;

import com.example.tasktable.R;
import com.example.tasktable.friday.FridaySQL;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

public class FridayActivityEdit extends Activity {

	EditText et;
	String gotBread;
	int gotBread_key;
	int c = 0;
	int c1 = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.day);
		et = (EditText) findViewById(R.id.editMonday);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("BASKET");
		gotBread_key = gotBasket.getInt("BASKET_KEY");

		if ((gotBread != null) && (!gotBread.isEmpty())) {
			et.setText(gotBread);
			et.setSelection(gotBread.length());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.menu_save, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@SuppressLint("NewApi")
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		String getText = et.getText().toString().trim();
		if (getText.equals("")) {
			getText = null;
		}
		c = 1;
		try {
			FridaySQL mySQL = new FridaySQL(FridayActivityEdit.this);
			mySQL.Open();
			if ((getText != null) && (!getText.isEmpty())) {
				mySQL.UpdateEntry(getText, gotBread, mySQL.getID(gotBread_key));
			} else {
				mySQL.DeleteEntry(gotBread, mySQL.getID(gotBread_key));
			}
			mySQL.Close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		Intent i = new Intent(FridayActivityEdit.this, Friday.class);
		startActivity(i);
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		c1 = 1;
		if (c == 0) {
			String getText = et.getText().toString().trim();
			if (getText.equals("")) {
				getText = null;
			}

			try {
				FridaySQL mySQL = new FridaySQL(FridayActivityEdit.this);
				mySQL.Open();
				if ((getText != null) && (!getText.isEmpty())) {
					mySQL.UpdateEntry(getText, gotBread,
							mySQL.getID(gotBread_key));
				} else {
					mySQL.DeleteEntry(gotBread, mySQL.getID(gotBread_key));
				}
				mySQL.Close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			c = 1;
			String getText = et.getText().toString().trim();
			if (getText.equals("")) {
				getText = null;
			}
			try {
				FridaySQL mySQL = new FridaySQL(FridayActivityEdit.this);
				mySQL.Open();
				if ((getText != null) && (!getText.isEmpty())) {
					mySQL.UpdateEntry(getText, gotBread,
							mySQL.getID(gotBread_key));
				} else {
					mySQL.DeleteEntry(gotBread, mySQL.getID(gotBread_key));
				}
				mySQL.Close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_save:
			c = 1;
			String getText1 = et.getText().toString().trim();
			if (getText1.equals("")) {
				getText1 = null;
			}
			try {
				FridaySQL mySQL = new FridaySQL(FridayActivityEdit.this);
				mySQL.Open();
				if ((getText1 != null) && (!getText1.isEmpty())) {
					mySQL.UpdateEntry(getText1, gotBread,
							mySQL.getID(gotBread_key));
				} else {
					mySQL.DeleteEntry(gotBread, mySQL.getID(gotBread_key));
				}
				mySQL.Close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			Intent i = new Intent(FridayActivityEdit.this, Friday.class);
			startActivity(i);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (c1 == 1) {
			Intent i = new Intent(FridayActivityEdit.this, Friday.class);
			startActivity(i);
			c=1;
		}
	}

}
