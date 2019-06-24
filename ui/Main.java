package ui;


import Model.ReadWebPage;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        ChoreTracker ct = new ChoreTracker();

        new ChoreTrackerGUI(new ChoreTracker());

        new ReadWebPage().readWebPage();
        try {
            ct.runChoreTracker();
        } catch (IOException e) {
            System.out.println("These files were not found");
            System.exit(0);
        }



    }
}

