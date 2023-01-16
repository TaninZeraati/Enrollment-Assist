package bdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.proprog.enrollassist.Exception.ExceptionList;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import ir.proprog.enrollassist.domain.EnrollmentRules.*;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.course.Course;

public class canBeTakenByBDD {
    @Autowired
    Course newCourse;

    @Autowired
    Student tanin;




    @Given("A Studen.")
    public void wantsToTakeCourse() throws ExceptionList {
        tanin = new Student("810197977", "Undergraduate");

    }

    @When("Wants to take a cource")
    public void takeNewCource() throws ExceptionList {
        newCourse = new Course("4444444", "MATH1", 3, "Undergraduate");
    }

    @Then("Check the PreRequisits are meet")
    public void checkCanBeTaken(){
        List<EnrollmentRuleViolation> violations = newCourse.canBeTakenBy(tanin);
        Assertions.assertTrue(violations.isEmpty());

    }
}