package storageMethods;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;

public class ioManager {

	// method for saving string data to device storage
	public static Boolean writeStringFile(Context context, String data, String name, Boolean isInternal)
	{
		// create the method fields required for writing the file
		File file;
		FileOutputStream fileOut;
		Boolean operationSuccess = true;
		
		try {
			
			// check if the write should be done to internal or external storage
			if (isInternal)
			{
				// open the file output stream for internal storage
				fileOut = context.openFileOutput(name, Context.MODE_PRIVATE);
			}
			else
			{
				// create a new file object in app directory with the passed in name
				file = new File(context.getExternalFilesDir(null), name);
				
				// open the file output stream for external storage
				fileOut = new FileOutputStream(file);
			}
			
			// convert the passed in data string into bytes
			// and write that to the specified storage
			fileOut.write(data.getBytes());
			
			// close the file output stream
			fileOut.close();
			
		} catch (IOException e) {
			operationSuccess = false;
			Log.e("FAILED WRITE", "File did not write successfully.");
		}
		
		// return whether the operation was successful or not
		return operationSuccess;
	}
	
	// method for saving object data to device storage
	public static Boolean writeObjectFile(Context context, Object data, String name, Boolean isInternal)
	{
		// create the method fields required for writing the file
		File file;
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		Boolean operationSuccess = true;
		
		try {
			
			// check if the write should be done to internal or external storage
			if (isInternal)
			{
				// open the file output stream for internal storage
				fileOut = context.openFileOutput(name, Context.MODE_PRIVATE);
			}
			else
			{
				// create a new file object in app directory with the passed in name
				file = new File(context.getExternalFilesDir(null), name);
				
				// open the file output stream for external storage
				fileOut = new FileOutputStream(file);
			}
			
			// create a new object output stream object using the specified file output stream
			objectOut = new ObjectOutputStream(fileOut);
			
			// write the passed in object to the specified device storage
			objectOut.writeObject(data);
			
			// close the object output stream
			objectOut.close();
			
			// close the file output stream
			fileOut.close();
			
		} catch (IOException e) {
			operationSuccess = false;
			Log.e("FAILED WRITE", "File did not write successfully.");
		}
		
		// return whether the operation was successful or not
		return operationSuccess;
	}
	
	public static HashMap<String, String> createStorageHash(String key, String value, HashMap<String, String> storageHash)
	{
		// check for non null hashMap parameter
		if (storageHash == null)
		{
			// create a new hashmap object
			storageHash = new HashMap<String, String>();
		}
		
		// set the passed in key value pair to the hash
		storageHash.put(key, value);
		
		return storageHash;
	}
}
