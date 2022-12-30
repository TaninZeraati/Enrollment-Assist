package ir.proprog.enrollassist.domain.enrollmentList;

import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.EnrollmentRules.*;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.studyRecord.Grade;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.section.Section;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.*;

import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.Assert.*;

public class checkValidGPALimitTest {
    Student owner;
    Student undergrad;
    Student undergradZero;
    Student undergradless12;
    Student undergradless17;
    Student phdstd;

    List<Section> sections;
    EnrollmentList underList;
    EnrollmentList undergradZeroList;
    EnrollmentList undergradless12List;
    EnrollmentList undergradless17List;
    EnrollmentList phdmaxcreditList;

    Set<StudyRecord> grades;


    @Before
    public void setup() throws ExceptionList {
        owner = new Student("7676", GraduateLevel.Masters.toString());
        undergrad = new Student("7575", GraduateLevel.Undergraduate.toString());
        undergradZero = new Student("7474", GraduateLevel.Undergraduate.toString());
        undergradless12 = new Student("7373", GraduateLevel.Undergraduate.toString());
        undergradless17 = new Student("7272", GraduateLevel.Undergraduate.toString());
        phdstd = new Student("7171", GraduateLevel.PHD.toString());

        Grade gpa = owner.calculateGPA();
        sections = new ArrayList<>();
        Course mincourse = new Course("0000001", "SoftwareTesting", 3, GraduateLevel.Masters.toString());
        Section nSection = new Section(mincourse, "11");

        Course underCourse = new Course("0000001", "SoftwareTesting", 2, GraduateLevel.Undergraduate.toString());
        Section underSection = new Section(underCourse, "12");
        underList = new EnrollmentList("temp", undergrad);

        underList.addSections(nSection);
        underList.addSections(nSection);
        underList.addSections(nSection);
        underList.addSections(nSection);
        underList.addSections(nSection);


        undergradZeroList = new EnrollmentList("temp", undergradZero);

        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);
        undergradZeroList.addSections(nSection);

        undergrad.setGrade( "00001",underCourse, 0);

        undergradless12List = new EnrollmentList("temp", undergradless12);

        undergradless12List.addSections(nSection);
        undergradless12List.addSections(nSection);
        undergradless12List.addSections(nSection);
        undergradless12List.addSections(nSection);
        undergradless12List.addSections(nSection);

        undergradless12.setGrade( "00001",underCourse, 10);

        undergradless17List = new EnrollmentList("temp", undergradless17);

        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);
        undergradless17List.addSections(nSection);

        undergradless17.setGrade( "00001",underCourse, 16);

        phdmaxcreditList = new EnrollmentList("temp", phdstd);

        phdmaxcreditList.addSections(nSection);
        phdmaxcreditList.addSections(nSection);
        phdmaxcreditList.addSections(nSection);
        phdmaxcreditList.addSections(nSection);
        phdmaxcreditList.addSections(nSection);

    }

    @After
    public void tearDown() {
        owner = null;
    }

    @Test
    public void MinCreditRequirementNotMeetTest() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();

        GraduateLevel ownerGraduateLevel = owner.getGraduateLevel();
        expectedViolations.add(new MinCreditsRequiredNotMet(ownerGraduateLevel.getMinValidTermCredit()));
        List<EnrollmentRuleViolation> violations = new EnrollmentList("temp", owner).checkValidGPALimit();

//        System.out.println(violations);

        assertEquals(expectedViolations.toString(), violations.toString());
    }

    @Test
    public void Max14Error() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();

        Grade gpa = undergrad.calculateGPA();



        GraduateLevel ownerGraduateLevel = undergrad.getGraduateLevel();
        expectedViolations.add(new MaxCreditsLimitExceeded(14));


        List<EnrollmentRuleViolation> violations = this.underList.checkValidGPALimit();

        assertEquals(expectedViolations.toString(), violations.toString());


    }

    @Test
    public void undergradZero() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();

        Grade gpa = undergradZero.calculateGPA();

        expectedViolations.add(new MaxCreditsLimitExceeded(20));


        List<EnrollmentRuleViolation> violations = this.undergradZeroList.checkValidGPALimit();

        assertEquals(expectedViolations.toString(), violations.toString());


    }

    @Test
    public void GPALess12Test() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();

        expectedViolations.add(new MaxCreditsLimitExceeded(14));


        List<EnrollmentRuleViolation> violations = this.undergradless12List.checkValidGPALimit();

        assertEquals(expectedViolations.toString(), violations.toString());


    }

    @Test
    public void GPALess17Test() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();

//        for checking workflow change this to 14 inorder to have error
        expectedViolations.add(new MaxCreditsLimitExceeded(14));
//        expectedViolations.add(new MaxCreditsLimitExceeded(20));


        List<EnrollmentRuleViolation> violations = this.undergradless17List.checkValidGPALimit();

        assertEquals(expectedViolations.toString(), violations.toString());


    }

    @Test
    public void getMaxValidCreditsTest() {
        List<EnrollmentRuleViolation> expectedViolations = new ArrayList<>();
        GraduateLevel ownerGraduateLevel = phdstd.getGraduateLevel();
        expectedViolations.add(new MaxCreditsLimitExceeded(ownerGraduateLevel.getMaxValidCredits()));


        List<EnrollmentRuleViolation> violations = this.phdmaxcreditList.checkValidGPALimit();

        assertEquals(expectedViolations.toString(), violations.toString());


    }

}
