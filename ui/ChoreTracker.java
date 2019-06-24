package ui;

import Exceptions.ChoreNotFoundException;
import Exceptions.NotFoundException;
import Exceptions.RoommateNotFoundException;
import Model.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChoreTracker implements SaveFile, LoadFile{
    private ArrayList<String> choreLog = new ArrayList<>();
    private Chores chores = new Chores();
    private Roommates roommates = new Roommates();
    private Chore currentChore = null;
    private Roommate currentRoommate = null;
    private String input;


    public ChoreTracker() {
    }


    public void runChoreTracker() throws IOException {
        LocalDate d;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        String ALREADYCOMPLETED = " was already completed by ";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Load files?");
        input = scanner.nextLine();

        if (input.equals("yes")) {
            loadFile();
        } else {

            System.out.println("Please enter name of roommates");
            while (true) {
                input = scanner.nextLine();
                if (!input.equals("done")) {
                    Roommate r = new Roommate(input);
                        roommates.addRoommates(r);
                } else break;
            }


            System.out.println("Please enter name of chores");

            while(true) {
                input = scanner.nextLine();
                if (!input.equals("done")) {
                    Chore dc = new DoChore(input);
                    chores.addChores(dc);
                }
                else break;
            }
        }

        chores.setNextChores();

        System.out.println("Please enter things that need to be purchased");
        while(true) {
            input = scanner.nextLine();
            if (!input.equals("done")) {
                Chore dc = new BuyChore(input);
                chores.addChores(dc);
            }
            else break;
        }

        for (Roommate r: roommates.getRoommateHashMap().values()
             ) {
            System.out.println("Please assign chores to " + r.getName());
            while (true) {
                input = scanner.nextLine();
                if (!input.equals("done")){
                    try {
                        Chore c = chores.findChore(input);
                        r.assignChore(c);
                        c.addChoreObserver(r);
                    } catch (ChoreNotFoundException e) {
                        System.out.println("Task was not found");
                    }
                }
                else break;
            }
        }


        roommates.printRoommates();
        chores.printChores();



        while (true) {

            System.out.println("Please enter your name");
            input = (scanner.nextLine());

            if (input.equals("quit")){
                break;
            }

            try {
                currentRoommate = roommates.findRoommate(input);
                System.out.println("What task did do you do/buy?");
                input = scanner.nextLine();
                currentChore = chores.findChore(input);
            }
            catch(RoommateNotFoundException e){
                System.out.println("There is no such roommate");
                continue;
            }
            catch(NotFoundException e){
                System.out.println("Task was not found");
                continue;
            }
            finally {
                System.out.println("Please continue");
            }


               if (!currentChore.getStatus()) {
                   while (true) {
                       System.out.println("When did you complete this task (mm/dd/yyyy)");
                       input = scanner.nextLine();
                       try {
                           d = LocalDate.parse(input, formatter);
                       } catch (Exception e) {
                           System.out.println("This is not a valid date, please retry");
                           continue;
                       }

                       while (currentChore instanceof BuyChore) {
                           System.out.println("How much did this cost?");
                           input = scanner.nextLine();

                           try {
                               ((BuyChore) currentChore).setPrice(Integer.parseInt(input));
                               break;
                           } catch (Exception e) {
                               System.out.println("This is not a valid price, please retry");
                           }
                       }

                       currentChore.print();
                       currentChore.notifyObservers(currentChore);

                       break;
                   }

               } else {
                    System.out.println(currentChore.getTask() + ALREADYCOMPLETED + currentChore.getCompletedBy().getName());
                    continue;
                }

            currentChore.completeChore(currentRoommate, d);
            choreLog.add(currentChore.choreEntry());
        }

        for (String entry : choreLog) {
            System.out.println(entry);
        }
        saveFile();
    }

    public void addToChoreLog(String s){
        choreLog.add(s);
    }




    @Override
    public void saveFile() throws IOException {
        PrintWriter p1 = new PrintWriter("Roommates.txt", "UTF-8");
        for (String rmt: roommates.getRoommateHashMap().keySet()){
            p1.println(rmt);
    }
        p1.close();

        PrintWriter p2 = new PrintWriter("DoChores.txt", "UTF-8");
        for (Chore c: chores.getChoreHashMap().values()) {
            if (c instanceof DoChore) {
                p2.println(c.getTask());
            }
        }
        p2.close();

        PrintWriter p3 = new PrintWriter("BuyChores.txt", "UTF-8");
        for (Chore c: chores.getChoreHashMap().values()){
            if (!c.getStatus()){
                if (c instanceof BuyChore)
                p3.println(c.getTask());
            }
        }
        p3.close();

        PrintWriter pc = new PrintWriter("CLog.txt", "UTF-8");
        for (String log: choreLog){
            pc.println(log);
        }
        pc.close();
    }

    @Override
    public void loadFile () throws IOException {
        List<String> rs = Files.readAllLines(Paths.get("Roommates.txt"));
        List<String> cs = Files.readAllLines(Paths.get("DoChores.txt"));
        List <String> bcs = Files.readAllLines(Paths.get("BuyChores.txt"));
        List<String> cl = Files.readAllLines(Paths.get("CLog.txt"));



        for (String rLine: rs){
            roommates.addRoommate(new Roommate(rLine));
        }

        for (String cLine: cs){
            Chore dc = new DoChore(cLine);
            chores.addChore(dc);
        }

        for (String bcLine: bcs){
            Chore bc = new BuyChore(bcLine);
            chores.addChore(bc);
        }

        for (String cEntry: cl){
            choreLog.add(cEntry);
        }

    }

    public void setCurrentChore(Chore currentChore) {
        this.currentChore = currentChore;
    }

    public void setCurrentRoommate(Roommate currentRoommate) {
        this.currentRoommate = currentRoommate;
    }

    public void setChores(Chores chores) {
        this.chores = chores;
    }

    public void setChoreLog(ArrayList<String> choreLog) {
        this.choreLog = choreLog;
    }

    public void setRoommates(Roommates roommates) {
        this.roommates = roommates;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Chores getChores() {
        return chores;
    }

    public Roommates getRoommates() {
        return roommates;
    }

    public ArrayList<String> getChoreLog() {
        return choreLog;
    }

    public Chore getCurrentChore() {
        return currentChore;
    }

    public Roommate getCurrentRoommate() {
        return currentRoommate;
    }
}

