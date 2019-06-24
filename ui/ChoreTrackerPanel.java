package ui;

import Exceptions.ChoreNotFoundException;
import Exceptions.RoommateNotFoundException;
import Model.BuyChore;
import Model.Chore;
import Model.Roommate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.*;

public class ChoreTrackerPanel extends JPanel implements ActionListener{
    private JLabel roommateLabel = new JLabel("Roommate Name");
    private JLabel choreLabel = new JLabel("Chore Completed/Item Bought");
    private JLabel dateLabel = new JLabel("Date (mm/dd/yyyy");
    private JLabel priceLabel = new JLabel("Price (if bought item, otherwise leave blank)");
    private JTextField roommateField =  new JTextField(30);
    private JTextField choreField = new JTextField(30);
    private JTextField dateField = new JTextField(10);
    private JTextField priceField = new JTextField(4);
    private JButton inputbtn = new JButton("submit");
    private JLabel progressLabel = new JLabel();
    private JLabel completionLabel = new JLabel();
    private ChoreTrackerGUI choreTrackerGUI;
    JLabel choreLogLabel;
    JScrollPane choreLogScrollPane;
    JList list = new JList();
    DefaultListModel choreLogListModel = new DefaultListModel();
    private ChoreTracker choreTracker;


    public ChoreTrackerPanel(ChoreTracker ct, ChoreTrackerGUI ctg){
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setPreferredSize(new Dimension(350, 320));
        choreTracker=ct;
        choreTrackerGUI = ctg;
        inputbtn.setActionCommand("submit");
        inputbtn.addActionListener(this);
        choreLogLabel = new JLabel("Chore Log");
        choreLogScrollPane = new JScrollPane(list);
        choreLogScrollPane.setPreferredSize(new Dimension(300, 80));
        add(roommateLabel);
        add(roommateField);
        add(choreLabel);
        add(choreField);
        add(dateLabel);
        add(dateField);
        add(priceLabel);
        add(priceField);
        add(inputbtn);
        add(progressLabel);
        add(completionLabel);
        add(choreLogLabel);
        add(choreLogScrollPane);
    }

    public void loadChoreTracker(ChoreTracker ct){
        choreTracker = ct;
        choreLogListModel = new DefaultListModel();
        for (String r : choreTracker.getChoreLog()
                ) {
            if (!choreLogListModel.contains(r)) {
                choreLogListModel.addElement(r);
            }

            list.setModel(choreLogListModel);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("submit")){
            Roommate currentRoommate;
            Chore currentChore;
            String entry = "";
            LocalDate d;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");


            try {
                currentRoommate = choreTracker.getRoommates().findRoommate(roommateField.getText().toLowerCase());
                currentChore = choreTracker.getChores().findChore(choreField.getText().toLowerCase());

            }  catch (RoommateNotFoundException re) {
                progressLabel.setText("Roommate was not found");
                return;
            } catch (ChoreNotFoundException ce) {
                progressLabel.setText("Chore was not found");
                return;
            }

            try {
                d = LocalDate.parse(dateField.getText(), formatter);
            } catch (Exception de) {
                progressLabel.setText("This is not a valid date");
                return;
            }

            if(currentChore instanceof BuyChore) {
                try {
                    ((BuyChore) currentChore).setPrice(Integer.parseInt(priceField.getText()));
                } catch (Exception ie){
                    progressLabel.setText("This is not a valid price");
                    return;
                }
            }


            if(!currentChore.getStatus()) {
                currentChore.completeChore(currentRoommate, d);
                entry = currentChore.choreEntry();
                choreTracker.addToChoreLog(entry);
                currentChore.notifyObservers(currentChore);
                choreTrackerGUI.updateAssignPanel();
                completionLabel.setText(entry);
            }
            else progressLabel.setText(currentChore.getTask()+ " was already completed by " +currentChore.getCompletedBy().getName());


            if (!choreLogListModel.contains(entry)){
                choreLogListModel.addElement(entry);
            }

            list.setModel(choreLogListModel);

        }

    }

}
