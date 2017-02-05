package com.corelib.jaacommonlib;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Keyboard.java
 *
 * Keyboard manipulation.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class Keyboard
{
	/**
	 * constructor
	 */
	private Keyboard() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of FontCache is forbidden");
	}

	/**
	 * Hide the soft keyboard
	 *
	 * @param activity The activity
	 */
	public static void hideKeyboard(Activity activity)
	{
		View view = activity.getCurrentFocus();
		if (view == null) {
			return;
		}
		InputMethodManager imm =
				(InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}