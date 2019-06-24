package ui;

import Exceptions.ChoreNotFoundException;
import Model.Chore;
import Model.Roommate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignChoresPanel extends JPanel implements ActionListener{
    JLabel assignLabel;
    JList list = new JList();
    JScrollPane assignScrollPane;
    JTextField assignField;
    JLabel label;
    DefaultListModel assignListModel = new DefaultListModel();
    Roommate roommate;
    ChoreTracker choreTracker;


    public AssignChoresPanel(ChoreTracker ct, Roommate r){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(320, 320));
        setLayout(new FlowLayout());
        roommate = r;
        choreTracker = ct;
        list = new JList();
        setLayout(new FlowLayout());
        assignLabel = new JLabel("Please assign chores to " +r.getName());
        assignScrollPane = new JScrollPane(list);
        assignField = new JTextField(5);
        label = new JLabel("");
        JButton btn = new JButton("enter");
        btn.setActionCommand("assign chore");
        btn.addActionListener(this);

        assignScrollPane.setPreferredSize(new Dimension(250, 80));

        add(assignLabel);
        add(assignField);
        add(btn);
        add(assignScrollPane);
        add(label);


        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("assign chore")) {
            String input = assignField.getText().toLowerCase();
            Chore c;
            try {
                c = choreTracker.getChores().findChore(input);
            } catch (ChoreNotFoundException e1) {
                label.setText("This is not a chore");
                return;
            }

            roommate.assignChore(c);
            c.addChoreObserver(roommate);

            if (!assignListModel.contains(c)){
                assignListModel.addElement(c.getTask());
            }
            list.setModel(assignListModel);
            label.setText("Assigned!");

        }
    }

    public void updateAssignedChores() {
        assignListModel = new DefaultListModel();
        if (!roommate.getAssignedChores().isEmpty()) {
            for (Chore c : roommate.getAssignedChores()
                    ) {
                assignListModel.addElement(c.getTask());
            }
        }
        list.setModel(assignListModel);
    }
}
