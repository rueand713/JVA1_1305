package com.randerson.java1project2;

import interfaceElements.CreateUI;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// create the main UI layout, setting the params and the orientation. This is the parent view of the application views
		LinearLayout mainLayout = CreateUI.createLinearLayout(this, false, false);

		// set the view content
		setContentView(mainLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
