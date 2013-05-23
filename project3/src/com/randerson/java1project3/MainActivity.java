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

	// creates a new instance of the CreateUI class
	CreateUI UIFactory = new CreateUI(this);
	
	// creates the main, sub, detail, and detailViews layouts with the UIFactory instance
	LinearLayout mainLayout;
	LinearLayout subLayout;
	LinearLayout detailLayout;
	LinearLayout leftDetailView;
	LinearLayout rightDetailView;
	
	// creates the text views for the weather detail layout
	TextView currentConditionValue;
	TextView tempValue;
	TextView humidityValue;
	TextView windSpeedValue;
	TextView windDirValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// integers for representing the various view ids
		int textId = 133;
		int buttonId = 177;
		int radioId = 199;
		
		// api request string parts
		// the strings will be concatenated with the selected location and passed as the request
		String apiKey = getString(R.string.apikey);
		String restStringA = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=";
		String restStringB = "&format=json&num_of_days=5&key=" + apiKey;
		
		// strings for the various app views
		String instructions = getString(R.string.instructions);
		String title = getString(R.string.titletext);
		String btnText = getString(R.string.buttontext);
		
		// array for populating radio group
		String[] locales = {"Houston", "Miami", "New York", "Los Angeles", "Seattle", "Chicago"};
		
		// creates the main, sub, detail, and detailViews layouts with the UIFactory instance
		mainLayout = UIFactory.createLinearLayout(false, false);
		subLayout = UIFactory.createLinearLayout(true, true);
		detailLayout = UIFactory.createLinearLayout(true, true);
		leftDetailView = UIFactory.createLinearLayout(false, false);
		rightDetailView = UIFactory.createLinearLayout(false, false);
		
		// creates the text views for the weather detail layout
		currentConditionValue = UIFactory.createTextView(" ", 0);
		tempValue = UIFactory.createTextView(" ", 0);
		humidityValue = UIFactory.createTextView(" ", 0);
		windSpeedValue = UIFactory.createTextView(" ", 0);
		windDirValue = UIFactory.createTextView(" ", 0);
		
		// creates the textViews with the UIFactory instance
		TextView instText = UIFactory.createTextView(instructions, textId);
		TextView titleText = UIFactory.createTextView(title, textId * 2);
		
		// creates the text views for the weather detail layout
		TextView currentCondition = UIFactory.createTextView("Current Condition: ", 0);
		TextView temp = UIFactory.createTextView("Temperature: ", 0);
		TextView humidity = UIFactory.createTextView("Humidity: ", 0);
		TextView windSpeed = UIFactory.createTextView("Wind Speed: ", 0);
		TextView windDir = UIFactory.createTextView("Wind Direction: ", 0);
		
		// create the radiogroup with the UIFactory instance
		RadioGroup radios = UIFactory.createRadioGroup(locales, radioId);
		
		// creates the button with the UIFactory instance
		Button sendBtn = UIFactory.createButton(btnText, buttonId);
		
		// add the child views to the sublayout
		subLayout.addView(instText);
		subLayout.addView(radios);
		
		// add the child views to the left detailView layout
		leftDetailView.addView(currentCondition);
		leftDetailView.addView(temp);
		leftDetailView.addView(humidity);
		leftDetailView.addView(windSpeed);
		leftDetailView.addView(windDir);
		
		// add the child views to the right detaliView layout
		rightDetailView.addView(currentConditionValue);
		rightDetailView.addView(tempValue);
		rightDetailView.addView(humidityValue);
		rightDetailView.addView(windSpeedValue);
		rightDetailView.addView(windDirValue);
		
		// add the child views to the detail layout
		detailLayout.addView(leftDetailView);
		detailLayout.addView(rightDetailView);
		
		// add the child views to the mainlayout
		mainLayout.addView(titleText);
		mainLayout.addView(subLayout);
		mainLayout.addView(sendBtn);
		mainLayout.addView(detailLayout);
		
		// set the content view
		setContentView(mainLayout);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
