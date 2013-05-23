package com.randerson.java1project3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import storageMethods.ioManager;

import networkingMethods.connectionManager;
import handleJSON.JSONFactory;
import interfaceMethods.CreateUI;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
	//LinearLayout rightDetailView;
	
	// setup the views for weather detail layout
	TextView currentCondition;
	TextView temp;
	TextView humidity;
	TextView windSpeed;
	TextView windDir;
	TextView header;
	
	// setup the radio group
	RadioGroup radios;
	
	// Boolean for the connection status
	Boolean connected;
	
	// URL for the get request
	URL weatherURL;
	
	// setup the memory hash object
	HashMap<String, String> memHash;
	
	// setup the context
	Context _context;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set the context
		_context = this;
		
		// load the previous hashfile
		memHash = (HashMap<String, String>) ioManager.readObjectFile(this, "history", true);
		
		// checks if there is a network connection
		connected = connectionManager.getConnectionStatus(this);
		//String conType = connectionManager.getConnectionType(this);
		
		/*Toast msg = new Toast(this);
		msg.setDuration(15);
		
		if (connected)
		{
			// set the toast text
			msg.setText(conType + "Network detected");
		}
		else
		{
			// set the toast text
			msg.setText("No Connection");
		}
		
		// show the toast
		msg.show();*/
		
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
		//rightDetailView = UIFactory.createLinearLayout(false, false);
		
		// creates the text views for the weather detail layout
		/*currentConditionValue = UIFactory.createTextView("N/A", 1);
		tempValue = UIFactory.createTextView("N/A", 2);
		humidityValue = UIFactory.createTextView("N/A", 3);
		windSpeedValue = UIFactory.createTextView("N/A", 4);
		windDirValue = UIFactory.createTextView("N/A", 5);*/
		
		// creates the textViews with the UIFactory instance
		TextView instText = UIFactory.createTextView(instructions, textId);
		TextView titleText = UIFactory.createTextView(title, textId * 2);
		header = UIFactory.createTextView("", 0);
		
		// creates the text views for the weather detail layout
		currentCondition = UIFactory.createTextView("Current Condition: ", 0);
		temp = UIFactory.createTextView("Temperature: ", 0);
		humidity = UIFactory.createTextView("Humidity: ", 0);
		windSpeed = UIFactory.createTextView("Wind Speed: ", 0);
		windDir = UIFactory.createTextView("Wind Direction: ", 0);
		
		// adds previous values if they exist in file
		if (memHash != null)
		{
			// temp strings for the weather data
			String cc = memHash.get("cond");
			String tmp = memHash.get("temp");
			String hum = memHash.get("humi");
			String wsp = memHash.get("wspd");
			String wdi = memHash.get("wdir");
			
			// sets the last weather data saved/viewed
			currentCondition.setText("Current Condition: " + cc);
			temp.setText("Temperature: " + tmp);
			humidity.setText("Humidity: " + hum);
			windSpeed.setText("Wind Speed: " + wsp);
			windDir.setText("Wind Direction: " + wdi);
			
			// sets the previous header text
			header.setText("Previous Conditions");
		}
		
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
						
						// creates the new request object
						doRequest req = new doRequest();
						
						// starts the request
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
		leftDetailView.addView(header);
		leftDetailView.addView(currentCondition);
		leftDetailView.addView(temp);
		leftDetailView.addView(humidity);
		leftDetailView.addView(windSpeed);
		leftDetailView.addView(windDir);
		
		/*// add the child views to the right detaliView layout
		rightDetailView.addView(currentConditionValue);
		rightDetailView.addView(tempValue);
		rightDetailView.addView(humidityValue);
		rightDetailView.addView(windSpeedValue);
		rightDetailView.addView(windDirValue);*/
		
		// add the child views to the detail layout
		detailLayout.addView(leftDetailView);
		
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
			// that object is then queried for the particular key and the string is returned and set
			String tempf = JSONFactory.readJSONObject(result, "temp_F");
			String humidityf = JSONFactory.readJSONObject(result, "humidity");
			String condition = "";
			String windSpeedm = JSONFactory.readJSONObject(result, "windspeedMiles");
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
			currentCondition.setText("Current Condition: " + condition);
			humidity.setText("Humidity: " + humidityf);
			temp.setText("Temperature: " + tempf);
			windSpeed.setText("Wind Speed: " + windSpeedm);
			windDir.setText("Wind Direction: " + windDirection);
			header.setText("Current Conditions");
			
			// save the data to hash
			ioManager.setStorageHash("cond", condition, memHash);
			ioManager.setStorageHash("humi", humidityf, memHash);
			ioManager.setStorageHash("temp", tempf, memHash);
			ioManager.setStorageHash("wspd", windSpeedm, memHash);
			ioManager.setStorageHash("wdir", windDirection, memHash);
			
			// save the hash to internal storage
			ioManager.writeObjectFile(_context, memHash, "history", true);
		}
	}

}
