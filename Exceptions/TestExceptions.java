package Exceptions;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ChoreTracker;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class TestExceptions {
    Chores cl;
    ChoreTracker cts;
    Roommates rl;
    Roommate r1;
    Roommate r2;
    Roommate r3;
    DoChore c1;
    DoChore c2;
    DoChore c3;
    BuyChore bc1;
    BuyChore bc2;
    BuyChore bc3;
    String e1;
    String e2;
    String e3;
    HashMap<String, Roommate> rmap = new HashMap<>();
    HashMap<String, Chore> cmap = new HashMap<>();
    ArrayList<String> log = new ArrayList<>();


    @BeforeEach
    public void setup(){
        cts = new ChoreTracker();
        rl = new Roommates();
        r1 = new Roommate("a");
        r2 = new Roommate("b");
        r3 = new Roommate("c");
        rmap.put(r1.getName(), r1);
        rmap.put(r2.getName(), r2);
        rmap.put(r3.getName(), r3);
        c1 = new DoChore("1");
        c2 = new DoChore("2");
        c3 = new DoChore("3");
        cmap.put(c1.getTask(), c1);
        cmap.put(c2.getTask(), c2);
        cmap.put(c3.getTask(), c3);
        e1 = "a";
        e2 = "b";
        e3 = "c";
        log.add(e1);
        log.add(e2);
        log.add(e3);
        bc1 = new BuyChore("d");
        bc2 = new BuyChore("e");
        bc3 = new BuyChore("f");
        cmap.put(bc1.getTask(), bc1);
        cmap.put(bc2.getTask(), bc2);
        cmap.put(bc3.getTask(), bc3);
        cl.setChoreHashMap(cmap);
        rl.setRoommateHashMap(rmap);
        cts.setChores(cl);
        cts.setChoreLog(log);
        cts.setRoommates(rl);
    }

    @Test
    public void testNoExceptionsThrown(){
        try {
            cts.getRoommates().findRoommate("a");
            cl.findChore("1");
        }
        catch (RoommateNotFoundException e){
            fail("Roommate exception thrown");
        } catch (ChoreNotFoundException e) {
            fail("Chores exception thrown");
        }
    }

    @Test
    public void testRoommateExceptionsThrown(){
        try {
            cts.getRoommates().findRoommate("d");
            fail("Roommate exception not thrown");
            cl.findChore("d");
        }
        catch (RoommateNotFoundException e){

        } catch (ChoreNotFoundException e) {
            fail("Chores exception thrown");
        }
    }

    @Test
    public void testChoreExceptionsThrown() {
        try {
            cts.getRoommates().findRoommate("a");
            cts.getChores().findChore("10");
            fail("Roommate exception not thrown");

        } catch (RoommateNotFoundException e) {
            fail("Roommate exception thrown");
        } catch (ChoreNotFoundException e) {

        }
    }



    @Test
    public void testBothExceptionsThrown(){
        try {
            cts.getRoommates().findRoommate("d");
            fail("Roommate exception not thrown");
            cts.getChores().findChore("");
        }
        catch (RoommateNotFoundException e){
        }
        catch (ChoreNotFoundException e){
        }
    }










}
