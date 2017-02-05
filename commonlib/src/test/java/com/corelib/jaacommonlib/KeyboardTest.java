package com.corelib.jaacommonlib;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for Keyboard
 */
@SuppressWarnings("ALL")
public class KeyboardTest
{
	@Test
	public void testConstructor() throws IllegalAccessException, InstantiationException
	{
		final Class<?> clazz = Keyboard.class;
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
	public void testHideKeyboardNoView()
	{
		Activity activity = mock(Activity.class);
		Keyboard.hideKeyboard(activity);
	}

	@Test
	public void testHideKeyboard()
	{
		Activity activity = mock(Activity.class);
		View view = mock(View.class);
		InputMethodManager inputMethodManager = mock(InputMethodManager.class);

		when(activity.getCurrentFocus()).thenReturn(view);
		when(activity.getSystemService(Context.INPUT_METHOD_SERVICE)).thenReturn(inputMethodManager);

		Keyboard.hideKeyboard(activity);
	}
}