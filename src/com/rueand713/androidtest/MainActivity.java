package com.rueand713.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout lLayout;
	LinearLayout.LayoutParams layoutParams;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		lLayout = new LinearLayout(this);
		lLayout.setOrientation(LinearLayout.VERTICAL);
		layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		
		lLayout.setLayoutParams(layoutParams);
		
		TextView tView = new TextView(this);
		tView.setText(getString(R.string.text));
		
		lLayout.addView(tView);
		
		EditText eText = new EditText(this);
		eText.setHint(getString(R.string.hint));
		//lLayout.addView(eText);
		
		Button btn = new Button(this);
		btn.setText(getString(R.string.button));
		//lLayout.addView(btn);
		
		LinearLayout lLayoutH = new LinearLayout(this);
		lLayoutH.setOrientation(LinearLayout.HORIZONTAL);
		lLayoutH.setLayoutParams(layoutParams);
		
		lLayoutH.addView(eText);
		lLayoutH.addView(btn);
		
		lLayout.addView(lLayoutH);
		
		setContentView(lLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
