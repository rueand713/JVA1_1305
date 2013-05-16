package interfaceElements;

import android.content.Context;
import android.widget.LinearLayout;

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
			layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			// set the layout param for the linear layout
			thisLayout.setLayoutParams(layoutParams);
		}
		
		return thisLayout;
	}
	
}
