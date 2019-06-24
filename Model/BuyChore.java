package Model;

public class BuyChore extends Chore {
    int price;

    public BuyChore(String task) {
        setTask(task);

    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String choreEntry() {
        return (this.getDateCompleted() + ": " + this.getCompletedBy().getName() + " bought " + this.getTask() + " and spent $" + Integer.toString(price));
    }

    @Override
    public void print(){
        System.out.println("You bought " + getTask() + " for $" + getPrice());
    }
}
