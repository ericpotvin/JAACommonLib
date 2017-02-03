package com.corelib.jaacommonlib;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(android.util.Log.class)

/**
 * Unit test for Debug
 */
@SuppressWarnings("ALL")
public class DebugTest
{
	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = Debug.class;
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
	public void testLogTow() throws Exception
	{
		PowerMockito.mockStatic(Log.class);

		Debug.v("test", "test");

		PowerMockito.verifyStatic(times(1));
		Debug.v("test", "test");
	}

	@Test
	public void testLogThree() throws Exception
	{
		PowerMockito.mockStatic(Log.class);

		Debug.v("test", "test", "test");

		PowerMockito.verifyStatic(times(1));
		Debug.v("test", "test", "test");
	}
}