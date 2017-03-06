package com.corelib.jaacommonlib;

import android.text.Editable;
import android.widget.EditText;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unused")
public class LayoutHelperTest
{
    @Test
    public void testConstructor() throws IllegalAccessException, InstantiationException
    {
        final Class<?> clazz = LayoutHelper.class;
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
    public void testDefault()
    {
        EditText input = mock(EditText.class);
        Editable editable = mock(Editable.class);

        when(editable.toString()).thenReturn("");
        when(input.getText()).thenReturn(editable);
        when(input.getText().toString()).thenReturn("");

        assertTrue(LayoutHelper.getEditTextNumber(input).intValue() == 0);
    }

    @Test
    public void testInt()
    {
        EditText input = mock(EditText.class);
        Editable editable = mock(Editable.class);

        when(editable.toString()).thenReturn("12");
        when(input.getText()).thenReturn(editable);
        when(input.getText().toString()).thenReturn("12");

        assertTrue(LayoutHelper.getEditTextNumber(input).intValue() == 12);

        when(editable.toString()).thenReturn("999");
        when(input.getText()).thenReturn(editable);
        when(input.getText().toString()).thenReturn("999");

        assertTrue(LayoutHelper.getEditTextNumber(input).intValue() == 999);
    }

    @Test
    public void testFloat()
    {
        EditText input = mock(EditText.class);
        Editable editable = mock(Editable.class);

        when(editable.toString()).thenReturn("12.17");
        when(input.getText()).thenReturn(editable);
        when(input.getText().toString()).thenReturn("12.17");

        assertTrue(LayoutHelper.getEditTextNumber(input).floatValue() == 12.17f);

        when(editable.toString()).thenReturn("999.999");
        when(input.getText()).thenReturn(editable);
        when(input.getText().toString()).thenReturn("999.999");

        assertTrue(LayoutHelper.getEditTextNumber(input).floatValue() == 999.999f);
    }
}