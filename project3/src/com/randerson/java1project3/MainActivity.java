package com.randerson.java1project3;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import networkingMethods.connectionManager;
import handleJSON.JSONFactory;
import interfaceMethods.CreateUI;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	// creates a new instance of the CreateUI class
	CreateUI UIFactory = new CreateUI(this);
	
	// set up the main, sub, detail, and detailViews layouts
	LinearLayout mainLayout;
	LinearLayout subLayout;
	LinearLayout detailLayout;
	LinearLayout leftDetailView;
	LinearLayout rightDetailView;
	
	// setup the views for weather detail layout
	TextView currentConditionValue;
	TextView tempValue;
	TextView humidityValue;
	TextView windSpeedValue;
	TextView windDirValue;
	
	// setup the radio group
	RadioGroup radios;
	
	// Boolean for the connection status
	Boolean connected;
	
	// URL for the get request
	URL weatherURL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// checks if there is a network connection
		connected = connectionManager.getConnectionStatus(this);
		
		// integers for representing the various view ids
		int textId = 133;
		int buttonId = 177;
		int radioId = 199;
		
		// api request string parts
		// the strings will be concatenated with the selected location and passed as the request
		final String apiKey = getString(R.string.apikey);
		final String restStringA = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=";
		final String restStringB = "&format=json&num_of_days=5&key=" + apiKey;
		
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
		radios = UIFactory.createRadioGroup(locales, radioId);
		
		// creates the button with the UIFactory instance
		Button sendBtn = UIFactory.createButton(btnText, buttonId);
		
		// set up the on click for the send button
		sendBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// get the id of the currently selected radio button
				int selectedId = radios.getCheckedRadioButtonId();
				
				// create an instance of the radio button that is currently selected
				RadioButton rBtn = (RadioButton) findViewById(selectedId);
				
				// retrieve the selected button text value from the instance
				String selectedValue = rBtn.getText().toString();
				
				// check to make sure there was an active network
				if (connected)
				{
					try {
						// create a URL object from the api strings
						weatherURL = new URL(restStringA + selectedValue + restStringB);
						
						doRequest req = new doRequest();
						
						req.execute(weatherURL);
						
					} catch (MalformedURLException e) {
						Log.e("URL ERROR", "Malformed URL");
					}
					
				}
			}
		});
		
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
	
	// android class thread for asynchronous requests
	private class doRequest extends AsyncTask<URL, Void, String>
	{
		
		@Override
		protected String doInBackground(URL...urls)
		{
			// create a empty response string
			String response = "";
			
			// loop for making url request with URLs 
			for (URL url:urls)
			{
				// response gets set for each request made (in this instance just one)
				response = connectionManager.makeStringRequest(url);
			}
			
			// return the response text
			return response;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			// log the result text
			Log.i("RESPONSE", result);
			
			// create JSON objects from the result string
			// that object is then querried for the particular key and the string is returned and set
			String temp = JSONFactory.readJSONObject(result, "temp_F");
			String humidity = JSONFactory.readJSONObject(result, "humidity");
			String condition = "";
			String windSpeed = JSONFactory.readJSONObject(result, "windspeedMiles");
			String windDirection = JSONFactory.readJSONObject(result, "winddir16Point");
			
			// create a new separate JSON object
			JSONObject cc = JSONFactory.returnJSONObject(result);
			
			try {
				// retrieve the deep nested weather condition string
				condition = cc.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).getJSONArray("weatherDesc").getJSONObject(0).getString("value");
			} catch (JSONException e) {
				Log.e("JSON ERROR", "JSON Exception");
			}
			
			// set the detail view data
			currentConditionValue.setText(condition);
			humidityValue.setText(humidity);
			tempValue.setText(temp);
			windSpeedValue.setText(windSpeed);
			windDirValue.setText(windDirection);
		}
	}

}
