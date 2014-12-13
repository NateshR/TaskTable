package com.example.tasktable.wednesday;

import com.example.tasktable.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TaskAdapter1 extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	int pos;
	Button click;
	TextView textView;

	public TaskAdapter1(Context context, String[] values) {
		super(context, R.layout.list_drawer, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflator.inflate(R.layout.list_drawer, parent, false);
		textView = (TextView) rowView.findViewById(R.id.etList1);
		textView.setText(values[position]);

		return rowView;
	}

}
