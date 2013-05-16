package interfaceElements;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateUI {

	// method for creating and returning a linear layout object
	public static LinearLayout createLinearLayout (Context context, boolean isHorizontal, boolean wrapContent)
	{
		// create a new linear layout object for the passed in context
		LinearLayout thisLayout = new LinearLayout(context);
		
		// create a null layout param object
		LinearLayout.LayoutParams layoutParams;
		
		// check if the orientation should be horizontal or vertical
		if (isHorizontal)
		{
			// set the orientation horizontally
			thisLayout.setOrientation(LinearLayout.HORIZONTAL);
		}
		else
		{
			// set the orientation vertically
			thisLayout.setOrientation(LinearLayout.VERTICAL);
		}
		
		// check if the content should wrap or match its parent
		if (wrapContent)
		{
			// define the layout param object to wrap
			layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			// set the layout param for the linear layout
			thisLayout.setLayoutParams(layoutParams);
		}
		else
		{
			// define the layout param object to match its parent
			layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			
			// set the layout param for the linear layout
			thisLayout.setLayoutParams(layoutParams);
		}
		
		// return the object
		return thisLayout;
	}
	
	// method for creating and returing an editText object
	public static EditText createEditText(Context context, String hint, int id)
	{
		// creates a new editText object
		EditText thisEditText = new EditText(context);
		
		// set the editText object hint
		thisEditText.setHint(hint);
		
		// check for valid id to set otherwise ignores id setting
		if (id > 0) 
		{
			// set the editText object identifier
			thisEditText.setId(id);
		}
		
		// return the object
		return thisEditText;
	}
	
	// method for creating and returning a textView object
	public static TextView createTextView(Context context, String text, int id)
	{
		// creates a new textView object
		TextView thisTextView = new TextView(context);
		
		// sets the text for the textView object
		thisTextView.setText(text);
		
		// check for valid id to set otherwise ignores id setting
		if (id > 0) 
		{
			// sets the textView object identifier
			thisTextView.setId(id);
		}
		
		// return the object
		return thisTextView;
	}
	
	// method for creating and returning a button object
	public static Button createButton(Context context, String text, int id)
	{
		// creates a new button object
		Button thisButton = new Button(context);
		
		// sets the button text
		thisButton.setText(text);

		// check for valid id to set otherwise ignores id setting
		if (id > 0)
		{
			// sets the button object identifier
			thisButton.setId(id);
		}
		
		// return the object
		return thisButton;
	}
}
