package ir.proprog.enrollassist.domain.studyRecord;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class StudyRecordTest {
    private StudyRecord studyRecord;
//    private double grade;
    private GraduateLevel graduateLevel;
    private boolean expected;
    private Course course;

    public StudyRecordTest(String term, String courseNumber, String title, int credits, String gtlevel, double grade, GraduateLevel graduateLevel, boolean expected) throws ExceptionList {
        this.graduateLevel = graduateLevel;
        this.expected = expected;
        this.course = new Course(courseNumber,title, credits, gtlevel);
        this.studyRecord = new StudyRecord(term, course, grade);
//        this.grade = grade;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ExceptionList {
        return Arrays.asList(new Object[][]{
                {"00001", "7777777", "SoftwareTesting", 3, "Undergraduate", 8.5, GraduateLevel.Undergraduate, false},
                {"00001", "7777777", "SoftwareTesting", 3, "Undergraduate", 10.0, GraduateLevel.Undergraduate, true},
                {"00001", "7777777", "SoftwareTesting", 3, "Undergraduate", 12.75, GraduateLevel.Undergraduate, true},

                {"00001", "7777777", "SoftwareTesting", 3, "Masters", 8.5, GraduateLevel.Masters, false},
                {"00001", "7777777", "SoftwareTesting", 3, "Masters", 10.0, GraduateLevel.Masters, false},
                {"00001", "7777777", "SoftwareTesting", 3, "Masters", 12.0, GraduateLevel.Masters, true},
                {"00001", "7777777", "SoftwareTesting", 3, "Masters", 14.0, GraduateLevel.Masters, true},
//
                {"00001", "7777777", "SoftwareTesting", 3, "PHD", 9.0, GraduateLevel.PHD, false},
                {"00001", "7777777", "SoftwareTesting", 3, "PHD", 10.0, GraduateLevel.PHD, false},
                {"00001", "7777777", "SoftwareTesting", 3, "PHD", 14.0, GraduateLevel.PHD, true},
                {"00001", "7777777", "SoftwareTesting", 3, "PHD", 16.0, GraduateLevel.PHD, true},
//
                {"00002", "8888888", "Software", 3, "Undergraduate", 14.0, GraduateLevel.Masters, true},
                {"00002", "8888888", "Software", 3, "Undergraduate", 12.0, GraduateLevel.Masters, true},
                {"00002", "8888888", "Software", 3, "Undergraduate", 11.25, GraduateLevel.Masters, true},
//
                {"00002", "8888888", "Software", 3, "Undergraduate", 18.5, GraduateLevel.PHD, true},
                {"00002", "8888888", "Software", 3, "Undergraduate", 14.0, GraduateLevel.PHD, true},
                {"00002", "8888888", "Software", 3, "Undergraduate", 12.5, GraduateLevel.PHD, true},
        });
    }

    @Test
    public void isPassedTest() {
        assertEquals(studyRecord.isPassed(graduateLevel), this.expected);
    }
}