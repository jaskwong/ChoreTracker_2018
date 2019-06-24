package ui;

import Model.Chore;
import Model.Roommate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AssignPanel extends JPanel{
    private ChoreTracker choreTracker;
    List<AssignChoresPanel> lop = new ArrayList<>();

    public AssignPanel(ChoreTracker ct){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setLayout(new FlowLayout());
        choreTracker = ct;

        for (Roommate r: choreTracker.getRoommates().getRoommateHashMap().values()
                ) {

            AssignChoresPanel acp = new AssignChoresPanel(choreTracker, r);
            lop.add(acp);

            add(acp);
        }

        setVisible(true);
    }

    public void updateAssignedChores() {
        if(!lop.isEmpty()) {
            for (AssignChoresPanel p : lop
                    ) {
                p.updateAssignedChores();
            }
        }
    }



}
