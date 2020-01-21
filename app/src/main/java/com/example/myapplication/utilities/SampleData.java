package com.example.myapplication.utilities;

import com.example.myapplication.model.TermEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    public static final String SAMPLE_TERM_1 = "Term 1";
    public static final String SAMPLE_TERM_2 = "Term 2";
    public static final String SAMPLE_TERM_3 = "Term 3";

    private static Date getStartDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, diff);
        return cal.getTime();
    }

    private static Date getEndDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, diff + 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static List<TermEntity> getTerms() {
    List<TermEntity> terms = new ArrayList<>();
    terms.add(new TermEntity(SAMPLE_TERM_1, getStartDate(0), getEndDate(0)));
    terms.add(new TermEntity(SAMPLE_TERM_2, getStartDate(1), getEndDate(1)));
    terms.add(new TermEntity(SAMPLE_TERM_3, getStartDate(2), getEndDate(2)));
    return terms;
    }
}