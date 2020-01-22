package com.example.c196.utilities;

import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    public static final String SAMPLE_TERM_1 = "Term 1";
    public static final String SAMPLE_TERM_2 = "Term 2";
    public static final String SAMPLE_TERM_3 = "Term 3";
    public static final String SAMPLE_TERM_4 = "Fall Term";

    public static final String SAMPLE_COURSE_1 = "C196";
    public static final String SAMPLE_COURSE_2 = "C188";

    private static Date getTermStartDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, diff);
        return cal.getTime();
    }

    private static Date getTermEndDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, diff + 6);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    private static Date getCourseStartDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH, diff);
        return cal.getTime();
    }

    private static Date getCourseEndDate(int diff) {
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
    terms.add(new TermEntity(SAMPLE_TERM_1, getTermStartDate(0), getTermEndDate(0)));
    terms.add(new TermEntity(SAMPLE_TERM_2, getTermStartDate(1), getTermEndDate(1)));
    terms.add(new TermEntity(SAMPLE_TERM_3, getTermStartDate(2), getTermEndDate(2)));
    terms.add(new TermEntity(SAMPLE_TERM_4, getTermStartDate(3), getTermEndDate(3)));
    return terms;
    }

    public static List<CourseEntity> getCourses() {
        List<CourseEntity> courses = new ArrayList<>();
        courses.add(new CourseEntity(1, SAMPLE_COURSE_1, getCourseStartDate(0), getCourseEndDate(0),
                "test", "test", "test", "test"));
        courses.add(new CourseEntity(1, SAMPLE_COURSE_2, getCourseStartDate(1), getCourseEndDate(1),
                "test", "test", "test", "test"));
        return courses;
    }
}
