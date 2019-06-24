package Model;

import Observer.ChoreObserver;
import Observer.Subject;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Chore extends Subject {
    private String task = "";
    private Boolean status = false;
    private Roommate completedBy;
    private LocalDate dateCompleted;



    //EFFECTS sets whether or not a chore has been completed
    public void setStatus(Boolean status) {
        this.status = status;
    }

    //EFFECTS sets the name of the chore
    public void setTask(String task) {
        this.task = task;
    }

    public void setCompletedBy(Roommate newRoommate) {
        if (!newRoommate.equals(this.completedBy)) {
            if (completedBy != null)
                this.completedBy.setChore(null);

            this.completedBy = newRoommate;

            if (completedBy != null)
                newRoommate.setChore(this);
        }

    }

    //EFFECTS: returns the name of the chore
    public String getTask() {
        return task;
    }

    //EFFECTS: returns the status of the chore
    public Boolean getStatus() {
        return this.status;
    }

    //EFFECTS: returns the name of the roommate that completed the chore
    public Roommate getCompletedBy() {
        return completedBy;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public abstract void print();

    //MODIFIES: this
    //EFFECTS: changes the chore, from uncompleted to completed
    public void completeChore(Roommate r, LocalDate d) {
        if (!status) {
            status = true;
        }
        completedBy = r;
        dateCompleted = d;
    }

    public abstract String choreEntry();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chore)) return false;
        Chore chore = (Chore) o;
        return Objects.equals(task, chore.task);
    }

    @Override
    public int hashCode() {

        return Objects.hash(task);
    }
}
