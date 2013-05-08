package com.randerson.javaproject1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// method for creating and adding a view with a textEdit and button to the parent view
	// the string passed in is a reference to a value in the strings.xml resource file
	// the layout object should be the parent layout that the completed view should be added to
	public void addCharacterView(String name, LinearLayout layout)
	{
		// create a vertical sub-parent layout to house its own views
		// this view will then be added (along with its children) to the parent view
		LinearLayout vLayout = new LinearLayout(this);
		vLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams vLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		vLayout.setLayoutParams(vLayoutParams);
		
		// create the TextView object for describing the current monster
		// using the passed in name string and the description string.xml reference
		// also for referencing and separation of the UI elements
		TextView description = new TextView(this);
		description.setText(name + " " + getString(R.string.description));
		
		// create the editText and button objects to add them to the objectLayout view
		EditText monsterName = new EditText(this);
		monsterName.setHint(getString(R.string.namecreature));
		Button monsterSelect = new Button(this);
		monsterSelect.setText(name);
		
		// create monster class buttons to add to the view
		// the monster class is a reference to a string in the strings.xml resource file
		Button monsterClassCommon = new Button(this);
		monsterClassCommon.setText(getString(R.string.common));
		Button monsterClassVeteran = new Button(this);
		monsterClassVeteran.setText(getString(R.string.veteran));
		Button monsterClassEvolved = new Button(this);
		monsterClassEvolved.setText(getString(R.string.evolved));
		
		
		// create a horizontal layout to house the monster class buttons
		// and a horizontal layout to house the editText and monster select button
		// these views will then be added to the vertical layout view
		
		// layout for the monster buttons
		LinearLayout hLayoutA = new LinearLayout(this);
		hLayoutA.setOrientation(LinearLayout.HORIZONTAL);
		
		// layout for the monster selection
		LinearLayout hLayoutB = new LinearLayout(this);
		hLayoutB.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout.LayoutParams hLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		hLayoutA.setLayoutParams(hLayoutParams);
		hLayoutB.setLayoutParams(hLayoutParams);
		
		// add the sub views to their container view
		hLayoutA.addView(monsterClassCommon);
		hLayoutA.addView(monsterClassVeteran);
		hLayoutA.addView(monsterClassEvolved);
		
		hLayoutB.addView(monsterName);
		hLayoutB.addView(monsterSelect);
		
		// add the horizontal views to their parent vertical view
		vLayout.addView(hLayoutA);
		vLayout.addView(hLayoutB);
		
		// add the fully created view set to the main view
		layout.addView(vLayout);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set the initial values
		int totalPoints = 500;
		int[] monsterElements = {
				R.string.corpse, R.string.barbarian, R.string.mutant, 
				R.string.skeleton, R.string.orc, R.string.zombie,
				R.string.lycan, R.string.vampire, R.string.lich
				};
		
		
		// create the main layout. This view will be the 
		// top layer view for the application and will hold all
		// other views
		LinearLayout mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(layoutParams);
		
		// iterate through the monsterElements and create a linear layout 
		// for each of the monster classes
		for(int i = 0; i < monsterElements.length; i++)
		{
			addCharacterView(getString(monsterElements[i]), mainLayout);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
