package com.randerson.java1project2;

import handlingJSON.JSONFactory;
import interfaceElements.CreateUI;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set the strings from the resource file
		String paperboy = getString(R.string.paperboy);
		String lufia2 = getString(R.string.lufia2);
		String ki = getString(R.string.ki);
		String goldeneye = getString(R.string.goldeneye);
		String zelda = getString(R.string.zelda);
		String oblivion = getString(R.string.oblivion);
		String halo = getString(R.string.halo);
		String cod4 = getString(R.string.cod4);
		String bf2 = getString(R.string.battlefield);
		String minecraft = getString(R.string.minecraft);
		String headerText = getString(R.string.textHeader);
		String buttonText = getString(R.string.buttonText);
		
		// create a string array of the radio button values
		String[] radioValues = {paperboy, ki, lufia2, goldeneye, zelda, halo, oblivion, cod4, bf2, minecraft};
		
		// view ids
		int buttonId = 300;
		int textViewId = 500;
		int radioId = 700;
		int descId = 900;
		
		// create the main UI layout
		LinearLayout mainLayout = CreateUI.createLinearLayout(this, false, false);
		
		// create the title textView for the main layout
		TextView appHeader = CreateUI.createTextView(this, headerText, textViewId);
		
		// create the sub UI layout
		LinearLayout subLayout = CreateUI.createLinearLayout(this, true, true);
		
		// create the radio group for the sub layout
		final RadioGroup radios = CreateUI.createRadioGroup(this, radioValues, radioId);
		
		// create the description textView for the main layout
		final TextView description = CreateUI.createTextView(this, "", descId);
		
		// create the button for the sub layout
		Button btn = CreateUI.createButton(this, buttonText, buttonId);
				
		// set the button click event
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int id = radios.getCheckedRadioButtonId();
				
				RadioButton checkedBtn = (RadioButton) findViewById(id);
				
				String selectedValue = checkedBtn.getText().toString();
				
				String descString = JSONFactory.readJSONObject(selectedValue);
				
				description.setText(descString);
			}
		});
		
		// add the appheader to the main layout
		mainLayout.addView(appHeader);
		
		// add the child views to the sub layout
		subLayout.addView(radios);
		subLayout.addView(btn);
		
		// add the sub layout to the main layout
		mainLayout.addView(subLayout);
		
		// add the description textView to the main layout
		mainLayout.addView(description);

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
