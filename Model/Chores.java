package Model;

import Exceptions.ChoreNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class Chores {
    private HashMap<String, Chore> choreHashMap;


    public Chores(){
        this.choreHashMap = new HashMap<>();
    }

    public void setChoreHashMap(HashMap<String, Chore> choreHashMap) {
        this.choreHashMap = choreHashMap;
    }

    public HashMap<String, Chore> getChoreHashMap() {
        return choreHashMap;
    }

    public void addChore(Chore c){
        choreHashMap.put(c.getTask(), c);
    }

    public void addChores(Chore c) {
            if (!choreHashMap.containsValue(c)) {
                choreHashMap.put(c.getTask(), c);
            } else {
                System.out.println("This has already been added");
            }
    }

    // EFFECTS: Returns the chore matching given task
    public Chore findChore(String task) throws ChoreNotFoundException {
        Chore c = choreHashMap.get(task);
        if (!(c == null)) {
            return c;
        } else {
            throw new ChoreNotFoundException();
        }
    }

    // EFFECTS: Sets the next chore for all chores
    public void setNextChores() {
        ArrayList<Chore> loc = new ArrayList<>(choreHashMap.values());
        for (Chore c : loc
                ) {
            if (c instanceof DoChore) {
                int index = loc.indexOf(c);
                while (true) {
                    if (index < (loc.size() - 1)) {
                        Chore nc = loc.get((index + 1));
                        if (nc instanceof DoChore) {
                            ((DoChore) c).setNextChore((DoChore) nc);
                            break;
                        } else index++;
                    } else {
                        index = -1;
                    }
                }
            }
        }
    }

    // Prints the list of chores
    public void printChores(){
        ArrayList<Chore> doList = new ArrayList<>();
        ArrayList<Chore> buyList = new ArrayList<>();
        for (Chore c: choreHashMap.values()){
            if ((c instanceof DoChore)) {
                doList.add(c);
            } else {
                buyList.add(c);
            }
        }

        System.out.println("Things to do this week:");
        printList(doList);

        System.out.println("Things to buy this week:");
        printList(buyList);

    }

    private void printList(ArrayList<Chore> listOfChores) {
        for (Chore c: listOfChores){
            System.out.println("- " +c.getTask());
        }
    }

}
