package Model;

import Observer.ChoreObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Roommate implements ChoreObserver{
    private String name;
    private Chore chore;
    private List<Chore> assignedChores = new ArrayList<>();

    public Roommate(String name){
        this.name = name;

    }

    //MODIFIES: this
    //EFFECTS: sets the roommate name
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: returns the roommate name
    public String getName() {
        return name;
    }

    public void setChore(Chore newChore) {
        if (!newChore.equals(this.chore)) {
            if (chore != null)
                this.chore.setCompletedBy(null);

            this.chore = newChore;
                newChore.setCompletedBy(this);
        }
    }

    public void assignChore(Chore c){
        if(!assignedChores.contains(c)){
            assignedChores.add(c);
        }
    }

    public List<Chore> getAssignedChores() {
        return assignedChores;
    }

    @Override
    public void update(Chore c){
        System.out.println(c.getTask()+ " was completed");
        assignedChores.remove(c);
    }


//    public void update(Chore c){
//        System.out.println(c.getTask()+ " was completed");
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roommate roommate = (Roommate) o;
        return Objects.equals(name, roommate.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
