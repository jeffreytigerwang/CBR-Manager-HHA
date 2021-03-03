package com.example.cbr.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * String manipulation class
 * */

public class StringsUtil {

    private static final String UK_PATTERN = "dd-MMM-yyyy";

    public static String dateToUKFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(UK_PATTERN, Locale.UK);
        return format.format(date);
    }
}
