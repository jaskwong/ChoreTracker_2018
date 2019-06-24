package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ChoreTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class testSaveFile {
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
        cl = new Chores();
        rl = new Roommates();
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
    public void testSave() throws IOException {
        cts.saveFile();
        List<String> rs = Files.readAllLines(Paths.get("Roommates.txt"));
        List<String> cs = Files.readAllLines(Paths.get("DoChores.txt"));
        List<String> bcs = Files.readAllLines(Paths.get("BuyChores.txt"));
        List<String> ls = Files.readAllLines(Paths.get("CLog.txt"));

        assertEquals("a", rs.get(0));
        assertEquals("b", rs.get(1));
        assertEquals("c", rs.get(2));

        assertEquals("1", cs.get(0));
        assertEquals("2", cs.get(1));
        assertEquals("3", cs.get(2));

        assertEquals("a", ls.get(0));
        assertEquals("b", ls.get(1));
        assertEquals("c", ls.get(2));

        assertEquals("a", ls.get(0));
        assertEquals("b", ls.get(1));
        assertEquals("c", ls.get(2));

        assertEquals("d", bcs.get(0));
        assertEquals("e", bcs.get(1));
        assertEquals("f", bcs.get(2));
    }

    @Test
    public void testSaveBuyChoreCompleted() throws IOException {
        bc2.setStatus(true);

        cts.saveFile();
        List<String> bcs2 = Files.readAllLines(Paths.get("BuyChores.txt"));

        assertEquals("d", bcs2.get(0));
        assertEquals("f", bcs2.get(1));
        assertFalse(bcs2.contains("e"));


    }
}
