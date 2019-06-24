package ui;

import Model.BuyChore;
import Model.Chore;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuyChoresPanel extends ChoresPanel implements ActionListener {

    public BuyChoresPanel (ChoreTracker ct) {
        super(ct);
        setChoreLabeltext("Please enter things to be bought");
    }

    @Override
    public void loadChoreTracker(ChoreTracker ct){
        this.setChoreTracker(ct);
        List<String> clist = new ArrayList<>();
        setChoreListModel(new DefaultListModel());

        for (Chore c: this.getChoreTracker().getChores().getChoreHashMap().values()){
            if(c instanceof BuyChore){
                clist.add(c.getTask());
            }
        }

        for (String r : clist
                ) {
            if (!this.getChoreListModel().contains(r)) {
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
            input = this.getChoreField().getText();
            Chore c = new BuyChore(input.toLowerCase());
            addToChoresPanel(c);
        }
    }


}
