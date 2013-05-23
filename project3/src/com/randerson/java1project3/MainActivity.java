package com.randerson.java1project3;

import interfaceMethods.CreateUI;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// integers for representing the various view ids
		int textId = 133;
		int buttonId = 177;
		int radioId = 199;
		
		// strings for the various app views
		String instructions = getString(R.string.instructions);
		String title = getString(R.string.titletext);
		String btnText = getString(R.string.buttontext);
		
		// array for populating radio group
		String[] locales = {"Houston", "Miami", "New York", "Los Angeles", "Seattle", "Chicago"};
		
		// creates a new instance of the CreateUI class
		CreateUI UIFactory = new CreateUI(this);
		
		// creates the main and sub layouts with the UIFactory instance
		LinearLayout mainLayout = UIFactory.createLinearLayout(false, false);
		LinearLayout subLayout = UIFactory.createLinearLayout(true, true);
		
		// creates the textViews with the UIFactory instance
		TextView instText = UIFactory.createTextView(instructions, textId);
		TextView titleText = UIFactory.createTextView(title, textId * 2);
		
		// create the radiogroup with the UIFactory instance
		RadioGroup radios = UIFactory.createRadioGroup(locales, radioId);
		
		// creates the button with the UIFactory instance
		Button sendBtn = UIFactory.createButton(btnText, buttonId);
		
		
		// add the child views to the sublayout
		subLayout.addView(instText);
		subLayout.addView(radios);
		
		// add the child views to the mainlayout
		mainLayout.addView(titleText);
		mainLayout.addView(subLayout);
		mainLayout.addView(sendBtn);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
