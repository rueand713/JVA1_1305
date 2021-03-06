package handleJSON;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONFactory {

	public static String readJSONObject(String JSONString, String key)
	{
		// create a container string for the json value
		String jsonValue = "";
		
		// builds and returns a JSON object from the string
		JSONObject json = returnJSONObject(JSONString);
		
		try {
			// set the JsonValue string by querying through the json object
			jsonValue = json.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).getString(key);
			
			Log.i("JSON", jsonValue);
		} catch (JSONException e) {
			Log.e("ERROR", "JSON Exception error");
		}
		
		return jsonValue;
	}
	
	public static JSONObject returnJSONObject(String JSONString)
	{
		// create a null JSON object
		JSONObject thisJSON = null;
		
		try {
			
			// create a new JSON object with the passed in JSON string
			thisJSON = new JSONObject(JSONString);
			
		} catch (JSONException e) {
			Log.e("ERROR", "JSON Exception Error");
		}
		
		// return the object
		return thisJSON;
	}
	
}
