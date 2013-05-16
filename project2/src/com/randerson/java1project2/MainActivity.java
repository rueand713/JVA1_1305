/*
 * project 		java1Project2
 * 
 * package 		com.randerson.java1project2
 * 
 * @author 		Rueben Anderson
 * 
 * date			May 16, 2013
 * 
 */
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
		
		// create an instance of the CreateUI class
		CreateUI UIFactory = new CreateUI(this);
		
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
		LinearLayout mainLayout = UIFactory.createLinearLayout(false, false);
		
		// create the title textView for the main layout
		TextView appHeader = UIFactory.createTextView(headerText, textViewId);
		
		// create the sub UI layout
		LinearLayout subLayout = UIFactory.createLinearLayout(true, true);
		
		// create the radio group for the sub layout
		final RadioGroup radios = UIFactory.createRadioGroup(radioValues, radioId);
		
		// create the description textView for the main layout
		final TextView description = UIFactory.createTextView("", descId);
		
		// create the button for the sub layout
		Button btn = UIFactory.createButton(buttonText, buttonId);
				
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
		subLayout.addView(description);
		
		// add the sub layout to the main layout
		mainLayout.addView(subLayout);
		mainLayout.addView(btn);

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
