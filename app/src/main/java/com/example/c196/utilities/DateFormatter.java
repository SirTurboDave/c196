package com.example.c196.utilities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static String datePattern = "MMM-dd-yyyy";
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat(datePattern, Locale.getDefault());

    public static String format(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date parse(String dateText) throws ParseException {
        return simpleDateFormat.parse(dateText);
    }
}
