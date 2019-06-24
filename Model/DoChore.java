package Model;

public class DoChore extends Chore {
private DoChore nextChore;


    //EFFECTS: constructs a new chore
    public DoChore(String task) {
        setTask(task);

    }

    public void setNextChore(DoChore nextChore) {
        this.nextChore = nextChore;
    }

    @Override
    public String choreEntry() {
        return (this.getDateCompleted() + ": " + this.getCompletedBy().getName() + " completed " + this.getTask() + " Next week: " + nextChore.getTask());
    }

    @Override
    public void print(){
        System.out.println("Next week you have to do " + nextChore.getTask());
    }


    
}
