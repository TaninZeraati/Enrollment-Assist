package bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

package ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
package ir.proprog.enrollassist.domain.EnrollmentRules;
package ir.proprog.enrollassist.domain.student;

public class canBeTakenByBDD {
    @Autowired
    Cource newCource;

    @Autowired
    Student student;




    @Given("A Studen.")
    public void wantsToTakeCourse(){
        tanin = new Student("810197977", "Undergraduate");

    }

    @When("Wants to take a cource")
    public void takeNewCource(){
        newCource = new Course("4444444", "MATH1", 3, "Undergraduate");
    }

    @Then("Check the PreRequisits are meet")
    public void checkCanBeTaken(){
        List<EnrollmentRuleViolation> violations = newCource.canBeTakenBy(tanin);
        assertTrue(violations.isEmpty());

    }
}