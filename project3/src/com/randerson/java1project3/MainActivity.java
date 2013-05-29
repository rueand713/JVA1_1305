package com.randerson.java1project3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.randerson.java1project3.R.layout;

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
import android.widget.Toast;

public class MainActivity extends Activity {

	// creates a new instance of the CreateUI class
	CreateUI UIFactory = new CreateUI(this);
	
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
		
		setContentView(layout.radioform);
		
		// set the context
		_context = this;
		
		// load the previous hashfile
		memHash = (HashMap<String, String>) ioManager.readObjectFile(this, "history", true);
		
		// checks if there is a network connection
		connected = connectionManager.getConnectionStatus(this);
		
		// call the method for displaying the toast
		displayToast();
		
		// integers for representing the various view ids
		int textId = 133;
		int buttonId = 177;
		int radioId = 199;
		
		// api request string parts
		// the strings will be concatenated with the selected location and passed as the request
		final String apiKey = getString(R.string.apikey);
		final String restStringA = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=";
		final String restStringB = "&format=json&num_of_days=5&key=" + apiKey;
		
		// creates the textViews with the UIFactory instance
		header = (TextView) findViewById(R.id.conditionheader);
		
		// creates the text views for the weather detail layout
		currentCondition = (TextView) findViewById(R.id.currentcondition);
		temp = (TextView) findViewById(R.id.tempcondition);
		humidity = (TextView) findViewById(R.id.humiditycondition);
		windSpeed = (TextView) findViewById(R.id.winspdcondition);
		windDir = (TextView) findViewById(R.id.windircondition);
		
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
			currentCondition.setText(cc);
			temp.setText(tmp);
			humidity.setText(hum);
			windSpeed.setText(wsp);
			windDir.setText(wdi);
			
			// sets the previous header text
			header.setText("Previous Conditions");
		}
		
		// create the radiogroup with the UIFactory instance
		radios = (RadioGroup) findViewById(R.id.locales);
		
		// creates the button with the UIFactory instance
		Button sendBtn = (Button) findViewById(R.id.weather_btn);
		
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
				
				// checks if there is a network connection
				connected = connectionManager.getConnectionStatus(_context);
				
				// call the method for displaying the toast
				displayToast();
				
				// check to make sure there was an active network
				if (connected == true)
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// method for showing the proper toast
	private void displayToast()
	{
		String conType = connectionManager.getConnectionType(this);
		
		// create the Toast object
		Toast msg = UIFactory.createToast("", true);
		
		if (connected == true)
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
		msg.show();
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
			currentCondition.setText(condition);
			humidity.setText(humidityf);
			temp.setText(tempf);
			windSpeed.setText(windSpeedm);
			windDir.setText(windDirection);
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
