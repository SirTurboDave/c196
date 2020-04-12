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
    public static final String SAMPLE_TERM_3 = "Spring Term";
    public static final String SAMPLE_TERM_4 = "Fall Term";

    public static final String SAMPLE_COURSE_1 = "C196";
    public static final String SAMPLE_COURSE_2 = "C188";
    public static final String SAMPLE_COURSE_3 = "C255";
    public static final String SAMPLE_COURSE_4 = "C164";
    public static final String SAMPLE_COURSE_5 = "C175";
    public static final String SAMPLE_COURSE_6 = "C459";
    public static final String SAMPLE_COURSE_7 = "C456";
    public static final String SAMPLE_COURSE_8 = "C455";
    public static final String SAMPLE_COURSE_9 = "C168";
    public static final String SAMPLE_COURSE_10 = "C278";
    public static final String SAMPLE_COURSE_11 = "C683";
    public static final String SAMPLE_COURSE_12 = "C768";

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
    terms.add(new TermEntity(1, SAMPLE_TERM_1, getTermStartDate(0), getTermEndDate(0)));
    terms.add(new TermEntity(2, SAMPLE_TERM_2, getTermStartDate(1), getTermEndDate(1)));
    terms.add(new TermEntity(3, SAMPLE_TERM_3, getTermStartDate(2), getTermEndDate(2)));
    terms.add(new TermEntity(4, SAMPLE_TERM_4, getTermStartDate(3), getTermEndDate(3)));
    return terms;
    }

    public static List<CourseEntity> getCourses() {
        List<CourseEntity> courses = new ArrayList<>();
        courses.add(new CourseEntity(1, SAMPLE_COURSE_1, getCourseStartDate(0),
                getCourseEndDate(0),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(1, SAMPLE_COURSE_2, getCourseStartDate(1),
                getCourseEndDate(1),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(1, SAMPLE_COURSE_3, getCourseStartDate(2),
                getCourseEndDate(2),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_4, getCourseStartDate(3),
                getCourseEndDate(3),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_5, getCourseStartDate(4),
                getCourseEndDate(4),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_6, getCourseStartDate(5),
                getCourseEndDate(5),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_7, getCourseStartDate(6),
                getCourseEndDate(6),"test", "testNote",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_8, getCourseStartDate(7),
                getCourseEndDate(7),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_9, getCourseStartDate(8),
                getCourseEndDate(8),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_10, getCourseStartDate(9),
                getCourseEndDate(9),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_11, getCourseStartDate(10),
                getCourseEndDate(10),"test", "test",
                "test", "test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_12, getCourseStartDate(11),
                getCourseEndDate(11),"test", "test",
                "test", "test", "testNote"));
        return courses;
    }
}
