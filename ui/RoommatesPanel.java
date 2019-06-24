package ui;

import Model.Roommate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RoommatesPanel extends JPanel implements ActionListener {
    JTextField roommateField;
    JLabel roommateLabel;
    JScrollPane roommateScrollPane;
    JList list = new JList();
    JLabel label;
    DefaultListModel roommateListModel = new DefaultListModel();
    ChoreTrackerGUI choreTrackerGUI;
    ChoreTracker choreTracker;




    public RoommatesPanel(ChoreTracker ct, ChoreTrackerGUI ctg) {
        choreTracker = ct;
        choreTrackerGUI = ctg;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(400, 320));
        setLayout(new FlowLayout());
        roommateLabel = new JLabel("Please enter name of roommates");
        roommateScrollPane = new JScrollPane(list);
        roommateScrollPane.setPreferredSize(new Dimension(250, 80));
        JButton btn = new JButton("enter");
        btn.setActionCommand("new roommate");
        btn.addActionListener(this);
        btn.addActionListener(ctg);
        JButton donebtn = new JButton("Assign Roommates");
        donebtn.setActionCommand("done");
        donebtn.addActionListener(this);
        roommateField = new JTextField(5);
        label = new JLabel("");
        add(roommateLabel);
        add(roommateField);
        add(btn);
        add(roommateScrollPane);
        add(donebtn);
        add(label);
        setVisible(true);

    }

    public void loadChoreTracker(ChoreTracker ct){
        choreTracker = ct;
        List<String> rlist = new ArrayList<>();
        rlist.addAll(choreTracker.getRoommates().getRoommateHashMap().keySet());
        roommateListModel = new DefaultListModel();

        for (String r : rlist
                ) {
            if (!roommateListModel.contains(r)) {
                roommateListModel.addElement(r);
                list.setModel(roommateListModel);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("new roommate")) {
            String input;
            input = roommateField.getText();
            Roommate r = new Roommate(input.toLowerCase());
            choreTracker.getRoommates().addRoommates(r);
            if (!roommateListModel.contains(r.getName())) {
                roommateListModel.addElement(r.getName());
                label.setText("Added!");

            } else {
                label.setText("This has already been added");
            }

            list.setModel(roommateListModel);

        }

        if (e.getActionCommand().equals("done")) {
            choreTrackerGUI.setAssignPanel();
            label.setText("Roommates assigned!");
        }

    }
}


