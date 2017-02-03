package com.corelib.jaacommonlib;

import android.util.Log;

/**
 * Debug.java
 *
 * Debug class.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public final class Debug
{
	/**
	 * constructor
	 */
	private Debug() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of FontCache is forbidden");
	}

	/**
	 * v()
	 * Verbose debug
	 *
	 * @param tag The class tag
	 * @param msg The message
	 * @param method The class method called
	 */
	public static void v(String tag, String msg, String method)
	{
		Log.v(" -- " + tag + ": ", "(" + method + ") " + msg);
	}

	/**
	 * v()
	 * Verbose debug
	 *
	 * @param tag The class tag
	 * @param msg The message
	 */
	public static void v(String tag, String msg)
	{
		Log.v(" -- " + tag + ": ", msg);
	}
}