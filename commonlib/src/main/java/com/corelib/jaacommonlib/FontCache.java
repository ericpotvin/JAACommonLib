package com.corelib.jaacommonlib;

import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

/**
 * FontCache.java
 *
 * Cache TTF.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public final class FontCache
{
	/**
	 * Cached fontCache
	 */
	private static Map<String, Typeface> cachedFonts = new HashMap<String, Typeface>();

	/**
	 * constructor
	 */
	private FontCache() throws InstantiationException
	{
		throw new InstantiationException("Create an instance of FontCache is forbidden");
	}

	/**
	 * Get the font
	 *
	 * @param filename The font filename
	 * @return TypeFace
	 */
	public static Typeface get(String filename)
	{
		if (!cachedFonts.containsKey(filename)) {
			Typeface tf = Typeface.createFromFile(filename);
			cachedFonts.put(filename, tf);
		}
		return cachedFonts.get(filename);
	}
}
