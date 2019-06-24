package ui;

import Model.Chore;
import Model.DoChore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DoChoresPanel extends ChoresPanel implements ActionListener {

    public DoChoresPanel(ChoreTracker ct) {
        super(ct);
        setChoreLabeltext("Please enter chores");
        JButton donebtn = new JButton("set chore cycle");
        donebtn.setActionCommand("done");
        donebtn.addActionListener(this);
        add(donebtn);
    }

    @Override
    public void loadChoreTracker(ChoreTracker ct){
        setChoreTracker(ct);
        List<String> clist = new ArrayList<>();
        setChoreListModel(new DefaultListModel());

        for (Chore c: getChoreTracker().getChores().getChoreHashMap().values()){
            if(c instanceof DoChore){
                clist.add(c.getTask());
            }
        }

        for (String r : clist
                ) {
            if (!getChoreListModel().contains(r)) {
                addToChoreModel(r);
            }
        }

        if(getChoreListModel().isEmpty()){
            getList().setModel(new DefaultListModel());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("new chore")) {
            String input;
            input = getChoreField().getText();
            Chore c = new DoChore(input.toLowerCase());
            addToChoresPanel(c);
        }

        if (e.getActionCommand().equals("done")) {
            getChoreTracker().getChores().setNextChores();
            this.setLabeltext("Next chores set!");
        }
    }


}
