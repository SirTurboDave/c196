package com.example.c196.utilities;

import com.example.c196.database.AssessmentEntity;
import com.example.c196.database.CourseEntity;
import com.example.c196.database.TermEntity;

import java.lang.reflect.Array;
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

    public static final String SAMPLE_ASSESSMENT_1 = "Sample Assessment 1";
    public static final String SAMPLE_ASSESSMENT_2 = "Sample Assessment 2";
    public static final String SAMPLE_ASSESSMENT_3 = "Sample Assessment 3";
    public static final String SAMPLE_ASSESSMENT_4 = "Sample Assessment 4";
    public static final String SAMPLE_ASSESSMENT_5 = "Sample Assessment 5";
    public static final String SAMPLE_ASSESSMENT_6 = "Sample Assessment 6";
    public static final String SAMPLE_ASSESSMENT_7 = "Sample Assessment 7";
    public static final String SAMPLE_ASSESSMENT_8 = "Sample Assessment 8";
    public static final String SAMPLE_ASSESSMENT_9 = "Sample Assessment 9";
    public static final String SAMPLE_ASSESSMENT_10 = "Sample Assessment 10";
    public static final String SAMPLE_ASSESSMENT_11 = "Sample Assessment 11";
    public static final String SAMPLE_ASSESSMENT_12 = "Sample Assessment 12";
    public static final String SAMPLE_ASSESSMENT_13 = "Sample Assessment 13";
    public static final String SAMPLE_ASSESSMENT_14 = "Sample Assessment 14";
    public static final String SAMPLE_ASSESSMENT_15 = "Sample Assessment 15";
    public static final String SAMPLE_ASSESSMENT_16 = "Sample Assessment 16";


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
                getCourseEndDate(0),"test", "testNote"));
        courses.add(new CourseEntity(1, SAMPLE_COURSE_2, getCourseStartDate(1),
                getCourseEndDate(1),"test", "testNote"));
        courses.add(new CourseEntity(1, SAMPLE_COURSE_3, getCourseStartDate(2),
                getCourseEndDate(2),"test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_4, getCourseStartDate(3),
                getCourseEndDate(3),"test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_5, getCourseStartDate(4),
                getCourseEndDate(4),"test", "testNote"));
        courses.add(new CourseEntity(2, SAMPLE_COURSE_6, getCourseStartDate(5),
                getCourseEndDate(5),"test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_7, getCourseStartDate(6),
                getCourseEndDate(6),"test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_8, getCourseStartDate(7),
                getCourseEndDate(7),"test", "testNote"));
        courses.add(new CourseEntity(3, SAMPLE_COURSE_9, getCourseStartDate(8),
                getCourseEndDate(8),"test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_10, getCourseStartDate(9),
                getCourseEndDate(9),"test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_11, getCourseStartDate(10),
                getCourseEndDate(10),"test", "testNote"));
        courses.add(new CourseEntity(4, SAMPLE_COURSE_12, getCourseStartDate(11),
                getCourseEndDate(11),"test", "testNote"));
        return courses;
    }

    public static List<AssessmentEntity> getAssessments() {
        List<AssessmentEntity> assessments = new ArrayList<>();
        assessments.add(new AssessmentEntity(1, SAMPLE_ASSESSMENT_1,
                "Objective", getCourseEndDate(0)));
        assessments.add(new AssessmentEntity(1, SAMPLE_ASSESSMENT_2,
                "Performance", getCourseEndDate(0)));
        assessments.add(new AssessmentEntity(1, SAMPLE_ASSESSMENT_3,
                "Performance", getCourseEndDate(0)));
        assessments.add(new AssessmentEntity(1, SAMPLE_ASSESSMENT_4,
                "Objective", getCourseEndDate(0)));
        assessments.add(new AssessmentEntity(1, SAMPLE_ASSESSMENT_5,
                "Performance", getCourseEndDate(0)));
        assessments.add(new AssessmentEntity(2, SAMPLE_ASSESSMENT_6,
                "Objective", getCourseEndDate(1)));
        assessments.add(new AssessmentEntity(3, SAMPLE_ASSESSMENT_7,
                "Performance", getCourseEndDate(2)));
        assessments.add(new AssessmentEntity(4, SAMPLE_ASSESSMENT_8,
                "Objective", getCourseEndDate(3)));
        assessments.add(new AssessmentEntity(5, SAMPLE_ASSESSMENT_9,
                "Performance", getCourseEndDate(4)));
        assessments.add(new AssessmentEntity(6, SAMPLE_ASSESSMENT_10,
                "Objective", getCourseEndDate(5)));
        assessments.add(new AssessmentEntity(7, SAMPLE_ASSESSMENT_11,
                "Performance", getCourseEndDate(6)));
        assessments.add(new AssessmentEntity(8, SAMPLE_ASSESSMENT_12,
                "Objective", getCourseEndDate(7)));
        assessments.add(new AssessmentEntity(9, SAMPLE_ASSESSMENT_13,
                "Objective", getCourseEndDate(8)));
        assessments.add(new AssessmentEntity(10, SAMPLE_ASSESSMENT_14,
                "Objective", getCourseEndDate(9)));
        assessments.add(new AssessmentEntity(11, SAMPLE_ASSESSMENT_15,
                "Objective", getCourseEndDate(10)));
        assessments.add(new AssessmentEntity(12, SAMPLE_ASSESSMENT_16,
                "Objective", getCourseEndDate(11)));
        return assessments;
    }
}
