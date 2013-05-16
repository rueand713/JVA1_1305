/*
 * project 		java1Project2
 * 
 * package 		interfaceElements
 * 
 * @author 		Rueben Anderson
 * 
 * date			May 16, 2013
 * 
 */
package interfaceElements;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CreateUI {

	// create the class context field
	static Context context = null;
	
	// constructor method for instantiating the class
	// class has been written to require object instance creation to set the context
	public CreateUI(Context thisContext)
	{
		// set the context of the object
		context = thisContext;
	}
	
	// method for creating and returning a linear layout object
	public LinearLayout createLinearLayout (boolean isHorizontal, boolean wrapContent)
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
	public EditText createEditText(String hint, int id)
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
	public TextView createTextView(String text, int id)
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
	public Button createButton(String text, int id)
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
	
	// method for creating and returning a radio group
	public RadioGroup createRadioGroup(String[] values, int id)
	{
		// create a new radio group object
		RadioGroup radios = new RadioGroup(context);
		
		for (int i = 0; i < values.length; i++)
		{
			// create a new radio button object
			RadioButton rb = new RadioButton(context);
			
			// set the text of that radio button
			rb.setText(values[i]);
			
			// set the radio button id to one more than its index
			rb.setId(i+1);
			
			// add the radio button to the group
			radios.addView(rb);
		}
		
		// check for a valid id to set otherwise ignore
		if (id > 0)
		{
			// set the radio group object identifier
			radios.setId(id);
		}
		
		// return the object
		return radios;
	}
}
