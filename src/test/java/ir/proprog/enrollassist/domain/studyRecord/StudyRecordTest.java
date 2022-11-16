package ir.proprog.enrollassist.domain.studyRecord;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.course.Course;
import lombok.AccessLevel;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.Assumptions;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Theories.class)
public class StudyRecordTest{
    @DataPoints
    public static double[] grade() {
        return new double[]{
                12,
                10,
                8,
                10
        };
    }
    @DataPoints
    public static String[] glevel() {
        return new String[]{
                "Undergraduate",
                "Master",
                "Undergraduate",
                "PHD"
        };
    }
    @DataPoints
    public static GraduateLevel[] levels() {
        return new GraduateLevel[]{
                GraduateLevel.Undergraduate,
                GraduateLevel.Masters,
                GraduateLevel.PHD,
                GraduateLevel.Undergraduate
        };
    }



    @Theory
    public void isPassedTest(double grade, String glevel,GraduateLevel levels ) throws Exception {

        Assumptions.assumeTrue(grade > 0 && glevel != null);
        Course newcourse;
        newcourse = new Course("7777777", "SoftwareTesting", 3,glevel );
        String newterm = "00072";
        StudyRecord record;
        record = new StudyRecord(newterm, newcourse, grade);
        assertTrue(record.isPassed(levels));
    }
}