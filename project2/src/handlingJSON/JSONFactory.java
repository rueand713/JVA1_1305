/*
 * project 		java1Project2
 * 
 * package 		handlingJSON
 * 
 * @author 		Rueben Anderson
 * 
 * date			May 16, 2013
 * 
 */
package handlingJSON;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONFactory {

	// method for creating a JSON object with the JSONData enum data
	public static JSONObject createJSONObject()
	{
		// create the master JSON object
		JSONObject queryObject = new JSONObject();
		
		// create the parent JSON object
		JSONObject parentObject = new JSONObject();
		
		try {
			for (JSONData json : JSONData.values())
			{
				// create the child JSON object
				JSONObject childObject = new JSONObject();
				
				// add the enum data to the child JSON object
				childObject.put("developer", json.getDeveloper());
				childObject.put("platform", json.getPlatform());
				childObject.put("title", json.getTitle());
				childObject.put("release", json.getRelease());
				
				// add the child JSON object to its parent
				parentObject.put(json.name().toString(), childObject);
			}
			
			// add the parent JSON object to the master queryObject
			queryObject.put("query", parentObject);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return the new JSON object
		return queryObject;
	}
	
	// method for reading the JSON object returned from the createJSONObject method
	public static String readJSONObject(String selectString)
	{
		// Strings to hold the corresponding JSON data
		String developer;
		String platform;
		String release;
		String title;
		
		// the string of JSON data to be returned
		String returnString;
		
		// create the JSONObject from createJSONObject method
		JSONObject gamesObject = createJSONObject();
		
		try {
			
			// set the strings to their JSON object values
			developer = gamesObject.getJSONObject("query").getJSONObject(selectString).getString("developer");
			platform = gamesObject.getJSONObject("query").getJSONObject(selectString).getString("platform");
			release = gamesObject.getJSONObject("query").getJSONObject(selectString).getString("release");
			title = gamesObject.getJSONObject("query").getJSONObject(selectString).getString("title");
			
			// create the return string from the retrieved JSON values
			returnString = "Title: " + title + "\r\n"
						+ "Developed by " + developer + "\r\n"
						+ "Platform: " + platform + "\r\n"
						+ "Released in " + release;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// return the error in string
			returnString = e.toString();
		}
		
		return returnString;
	}
}
