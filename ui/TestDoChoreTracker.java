package ui;

import Model.DoChore;
import Model.Roommate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDoChoreTracker {
    DoChore c1;
    DoChore c2;
    DoChore c3;
    Roommate r1;
    ChoreTracker ct;
    LocalDate d;

    @BeforeEach
    public void setup() {
        c1 = new DoChore("a");
        c2 = new DoChore("b");
        c3 = new DoChore("c");
        r1 = new Roommate("");
        r1.setName("test");
        ct = new ChoreTracker();
        d = LocalDate.of(2018,01,01);

    }
/*  public void roommateCompleted(DoChore thisC, DoChore nextC, Roommate r){
        thisC.completeChore();
        thisC.setCompletedBy(r.getName());
        r.setLastChore(thisC.getTask());
        r.setNextChore(nextC.getTask());
        System.out.println("Next week you have to " + r.getNextChore());
        }
*/


    @Test
    // tests different chores
    public void testRoommateCompletedDifferent(){
        c1.completeChore(r1, d);

        assertTrue(c1.getStatus());
        assertFalse(c2.getStatus());
        assertEquals(r1, c1.getCompletedBy());

    }

    @Test
    // tests the same chore twice
    public void testRoommateCompletedSame(){

        assertFalse(c3.getStatus());

        c3.completeChore(r1, d);

        assertTrue(c3.getStatus());

    }
}


