package ui;


import Model.Chore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class ChoresPanel extends JPanel implements ActionListener {
    private JLabel label;
    private JTextField choreField;
    private JLabel choreLabel;
    private JScrollPane choresScrollPane;
    private JList list = new JList();
    private ChoreTracker choreTracker;
    private DefaultListModel choreListModel = new DefaultListModel();


    public ChoresPanel(ChoreTracker ct) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(350, 320));
        setLayout(new FlowLayout());
        choreLabel = new JLabel("Please enter chores that need to be done");
        choresScrollPane = new JScrollPane(list);
        choresScrollPane.setPreferredSize(new Dimension(250, 80));
        JButton btn = new JButton("enter");
        btn.setActionCommand("new chore");
        btn.addActionListener(this);
        choreField = new JTextField(5);
        label = new JLabel("");
        add(choreLabel);
        add(choreField);
        add(btn);
        add(choresScrollPane);
        add(label);
        setVisible(true);
        choreTracker = ct;
    }

    public void addToChoresPanel(Chore c){
        if(!choreTracker.getChores().getChoreHashMap().containsKey(c.getTask())) {
            choreTracker.getChores().addChores(c);
            choreListModel.addElement(c.getTask());
            label.setText("Added!");
        } else label.setText("This has already been added");
        list.setModel(choreListModel);
    }

    public void addToChoreModel(String s){
        choreListModel.addElement(s);
        list.setModel(choreListModel);
    }
    public abstract void loadChoreTracker(ChoreTracker ct);


    public void setChoreLabeltext (String s) {
        choreLabel.setText(s);
    }

    public void setLabeltext (String s) {
        label.setText(s);
    }

    public ChoreTracker getChoreTracker() {
        return choreTracker;
    }

    public DefaultListModel getChoreListModel() {
        return choreListModel;
    }

    public JLabel getChoreLabel() {
        return choreLabel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JList getList() {
        return list;
    }

    public JScrollPane getChoresScrollPane() {
        return choresScrollPane;
    }

    public JTextField getChoreField() {
        return choreField;
    }

    public void setChoreField(JTextField choreField) {
        this.choreField = choreField;
    }

    public void setChoreLabel(JLabel choreLabel) {
        this.choreLabel = choreLabel;
    }

    public void setChoreListModel(DefaultListModel choreListModel) {
        this.choreListModel = choreListModel;
    }

    public void setChoresScrollPane(JScrollPane choresScrollPane) {
        this.choresScrollPane = choresScrollPane;
    }

    public void setChoreTracker(ChoreTracker choreTracker) {
        this.choreTracker = choreTracker;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setList(JList list) {
        this.list = list;
    }

}

