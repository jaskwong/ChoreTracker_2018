package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ChoreTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testLoadFile {
    ChoreTracker cts = new ChoreTracker();
    ChoreTracker ctl;
    Chores cl;
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
    public void setup() {
        rl = new Roommates();
        cl = new Chores();
        ctl = new ChoreTracker();
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
        cts.setChoreLog(log);
        rl.setRoommateHashMap(rmap);
        cts.setRoommates(rl);
        cl.setChoreHashMap(cmap);
        cts.setChores(cl);
    }

    @Test
    public void testLoadFile() throws IOException {
        ArrayList<String> testlog = new ArrayList<>();

        cts.saveFile();
        testLoad(ctl);

        assertTrue(ctl.getRoommates().getRoommateHashMap().containsKey("a"));
        assertTrue(ctl.getRoommates().getRoommateHashMap().containsKey("b"));
        assertTrue(ctl.getRoommates().getRoommateHashMap().containsKey("c"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("1"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("2"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("3"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("d"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("e"));
        assertTrue(ctl.getChores().getChoreHashMap().containsKey("f"));
        assertEquals("a", ctl.getChoreLog().get(0));
        assertEquals("b", ctl.getChoreLog().get(1));
        assertEquals("c", ctl.getChoreLog().get(2));



    }

    private void testLoad(ChoreTracker c) throws IOException {
        c.loadFile();
    }
}
