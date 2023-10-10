package bank.gobank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account {

    // the number of transaction a client is allowed to do per day
    private final IntegerProperty transactionLimit;



    public CheckingAccount(String owner, String accountNumber, double balance, int tLimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", tLimit);
    }

    public IntegerProperty transactionLimitProp() {

        return this.transactionLimit;
    }

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}
