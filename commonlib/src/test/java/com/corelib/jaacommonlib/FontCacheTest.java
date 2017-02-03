package com.corelib.jaacommonlib;


import android.graphics.Typeface;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for FontCache
 */
@SuppressWarnings("ALL")
public class FontCacheTest
{
	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = FontCache.class;
		final Constructor<?> classConstructor = clazz.getDeclaredConstructors()[0];
		classConstructor.setAccessible(true);

		Throwable targetException = null;

		try {
			classConstructor.newInstance((Object[])null);
		} catch (InvocationTargetException ex) {
			targetException = ex.getTargetException();
		}
		assertNotNull(targetException);
		assertEquals(targetException.getClass(), InstantiationException.class);
	}

	@Test
	public void testCacheNotFound()
	{
		Typeface typeface = FontCache.get("NOT_FOUND");
		assertTrue(typeface == null);
	}
}