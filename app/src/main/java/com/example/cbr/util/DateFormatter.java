package com.example.cbr.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Factory class to convert Date given into another format
 * */

public class DateFormatter {

    private static final String UK_PATTERN = "dd-MMM-yyyy";

    public static String toUKFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(UK_PATTERN, Locale.UK);
        return format.format(date);
    }
}
