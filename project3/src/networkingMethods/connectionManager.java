package networkingMethods;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class connectionManager {

	static Boolean connectionStatus = false;
	static String connectionType = "No Connection";
	
	// getter method for retrieving the connectionType
	public static String getConnectionType(Context context)
	{
		// calls the method to check and retrieve the connection data
		fetchConnectionData(context);
				
		// return the connection type
		return connectionType;
	}
	
	// getter method for retrieving the connectionStatus
	public static Boolean getConnectionStatus(Context context)
	{
		// calls the method to check and retrieve the connection data
		fetchConnectionData(context);
		
		// return the connection status
		return connectionStatus;
	}
	
	//  method for retrieving the connection details and setting the class fields
	private static void fetchConnectionData(Context context)
	{
		// setup the connectivity manager object
		ConnectivityManager conMngr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		// instantiate the networkinfo object to hold the connectivity manager active network
		NetworkInfo net411 = conMngr.getActiveNetworkInfo();
		
		// check if the network info object is created
		if (net411 != null)
		{
			// the network info object is created now it checks for a connection
			// if there is a connection the connected and connectionType fields are set to reflect that data
			// otherwise the fields are left to their default values
			if (net411.isConnected())
			{
				connectionStatus = true;
				connectionType = net411.getTypeName();
			}
		}
		
	}
	
}
