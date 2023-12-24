import java.util.ArrayList;

public class MyDao implements Dao{

    @Override
    public Receiver getReceiver(int num) {
        return null;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        return null;
    }

    @Override
    public Expense getExpense(int num) {
        return null;
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        return null;
    }

    @Override
    public int addReceiver(Receiver receiver) {
        return 0;
    }

    @Override
    public int addExpense(Expense expense) {
        return 0;
    }
}
