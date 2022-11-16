package ir.proprog.enrollassist.domain.section;

import org.junit.*;

import ir.proprog.enrollassist.Exception.ExceptionList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class PresentationScheduleTest {

    private PresentationSchedule schedule1;


    @BeforeEach
    public void setup() throws ExceptionList {

        this.schedule1 = new PresentationSchedule("Saturday","09:00","10:30");
    }

    @Test
    public void hasConflictTest() throws ExceptionList {
        PresentationSchedule schedule2 = new PresentationSchedule("Saturday","09:00", "10:30");
        Assertions.assertTrue(this.schedule1.hasConflict(schedule2));
    }

    @Test
    public void afterHasConflictTest() throws ExceptionList{
        PresentationSchedule schedule3 = new PresentationSchedule("Saturday","10:30", "12:00");
        Assertions.assertFalse(this.schedule1.hasConflict(schedule3));
    }

    @Test
    public void beforeHasConflictTest() throws ExceptionList{
        PresentationSchedule schedule4 = new PresentationSchedule("Saturday","07:00", "09:00");
        Assertions.assertFalse(this.schedule1.hasConflict(schedule4));
    }

    @Test
    public void otherdayHasConflictTest() throws ExceptionList{
        PresentationSchedule schedule5 = new PresentationSchedule("Sunday","09:00", "10:30");
        Assertions.assertFalse(this.schedule1.hasConflict(schedule5));
    }

}


