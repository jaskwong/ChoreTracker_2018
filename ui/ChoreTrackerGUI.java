package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.awt.GridBagConstraints.PAGE_END;


class ChoreTrackerGUI extends JFrame implements ActionListener {
    private RoommatesPanel rp;
    private ChoresPanel dcp;
    private ChoresPanel bcp;
    private ChoreTrackerPanel ctp;
    private AssignPanel ap;
    private ChoreTracker choreTracker;

    public ChoreTrackerGUI(ChoreTracker ct) {
        super("Chore Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        choreTracker = ct;
        setPreferredSize(new Dimension(1200, 1000));
        new BorderLayout(20, 20);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 13, 13));
        setLayout(new FlowLayout());
        JButton loadbtn = new JButton("load files");
        loadbtn.setActionCommand("load files");
        loadbtn.addActionListener(this);
        JButton savebtn = new JButton("save files");
        savebtn.setActionCommand("save files");
        savebtn.addActionListener(this);
        rp = new RoommatesPanel(choreTracker, this);
        dcp = new DoChoresPanel(choreTracker);
        bcp = new BuyChoresPanel(choreTracker);
        ctp = new ChoreTrackerPanel(choreTracker, this);
        add(rp);
        add(dcp);
        add(bcp);
        add(ctp);

        JPanel panel = new JPanel();
        panel.add(loadbtn);
        panel.add(savebtn);
        panel.setAlignmentY(PAGE_END);
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }



    public static void main(String[] args) {
        new ChoreTrackerGUI(new ChoreTracker());
    }

    public void setAssignPanel(){
        ap = new AssignPanel(choreTracker);
        add (ap);
        pack();
    }

    public void updateAssignPanel(){
        ap.updateAssignedChores();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load files")) {
            try {
                ChoreTracker ct = new ChoreTracker();
                ct.loadFile();
                choreTracker = ct;
                rp.loadChoreTracker(choreTracker);
                dcp.loadChoreTracker(choreTracker);
                bcp.loadChoreTracker(choreTracker);
                ctp.loadChoreTracker(choreTracker);
                ap = new AssignPanel(choreTracker);
                add (ap);
                pack();

                choreTracker.getChores().setNextChores();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getActionCommand().equals("save files")){
            try {
                choreTracker.saveFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}