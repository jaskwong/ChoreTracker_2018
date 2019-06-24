package Model;

import Exceptions.RoommateNotFoundException;

import java.util.HashMap;
import Observer.Subject;

public class Roommates {
    private HashMap<String, Roommate> roommateHashMap;

    public Roommates(){
        roommateHashMap = new HashMap<>();
    }

    public HashMap<String, Roommate> getRoommateHashMap() {
        return roommateHashMap;
    }

    public void addRoommate(Roommate r){
        roommateHashMap.put(r.getName(), r);
    }

    public void setRoommateHashMap(HashMap<String, Roommate> rmap){
        this.roommateHashMap = rmap;
    }


    public void addRoommates(Roommate r){
        if (!roommateHashMap.containsValue(r)) {
            roommateHashMap.put(r.getName(), r);
        } else {
            System.out.println("This has already been added");;
        }
    }

    public void printRoommates(){
        System.out.println("List of Roommates:");
        for (Roommate r: roommateHashMap.values()
                ) {
            System.out.println("- " +r.getName());
            for (Chore c: r.getAssignedChores()
                 ) {
                System.out.println("    - " +c.getTask());

            }

        }
    }

    // Returns the roommate that matches the given name
    public Roommate findRoommate(String name) throws RoommateNotFoundException {
        Roommate r = roommateHashMap.get(name);
        if (!(r==null)){
            return r;
        } else {
            throw new RoommateNotFoundException();
        }
    }
}
