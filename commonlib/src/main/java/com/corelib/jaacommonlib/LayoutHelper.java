package com.corelib.jaacommonlib;

import android.widget.EditText;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * LayoutHelper.java
 *
 * Layout Helper.
 *
 * @author	Eric Potvin
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class LayoutHelper
{

    /**
     * constructor
     */
    private LayoutHelper() throws InstantiationException
    {
        throw new InstantiationException("Create an instance of FontCache is forbidden");
    }

    /**
     * Get the value of the EditText as Number
     * @param e The edit text object
     * @return
     */
    public static Number getEditTextNumber(EditText e) {
        String value = e.getText().toString();
        if(value.isEmpty()) {
            value = "0";
            e.setText("0");
        }
        try {
            return NumberFormat.getInstance().parse(value);
        }
        catch (ParseException ex) {
            return 0;
        }
    }
}
