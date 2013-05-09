package com.randerson.javaproject1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// set up the initial values
	int numDogs = 0;
	String petType = "Dog";
	boolean aBool = true;
	
	LinearLayout mainLayout;
	LinearLayout subLayout;
	TextView dogs;
	EditText ownerName;
	
	public void addDog(LinearLayout layout)
	{
		if (aBool)
		{
			// set up the textview object
			TextView dogText = new TextView(this);
			dogText.setText("A " + petType + " was added.");
			
			// increment the number of dogs
			numDogs++;
			
			// add the view
			layout.addView(dogText);
			
			// update the dogs text
			dogs.setText("Dogs: " + numDogs);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the layouts
		mainLayout = new LinearLayout(this);
		subLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		subLayout.setOrientation(LinearLayout.HORIZONTAL);
	
		// setup the layout parameters
		LinearLayout.LayoutParams vParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		LinearLayout.LayoutParams hParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		mainLayout.setLayoutParams(vParams);
		subLayout.setLayoutParams(hParams);
		
		// set up the layout child objects
		ownerName = new EditText(this);
		TextView info = new TextView(this);
		dogs = new TextView(this);
		Button add1Btn = new Button(this);
		Button add5Btn = new Button(this);
		
		// set the child initial values
		ownerName.setHint("Input owner name");
		dogs.setText("Dogs: 0");
		info.setText(getString(R.string.instructions));
		add1Btn.setText("Add 1 Dog");
		add5Btn.setText("Add 5 Dogs");
		
		add1Btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				addDog(mainLayout);
			}
		});
		
		add5Btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				for (int i=0; i < 5; i++)
				{
					// add a dog for each loop
					addDog(mainLayout);
				}
			}
		});
		
		// add the child views to the subLayout
		subLayout.addView(ownerName);
		subLayout.addView(add1Btn);
		subLayout.addView(add5Btn);
		
		// add the child views to the main layout
		mainLayout.addView(dogs);
		mainLayout.addView(info);
		mainLayout.addView(subLayout);
		setContentView(mainLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
