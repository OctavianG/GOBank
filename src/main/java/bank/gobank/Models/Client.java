package bank.gobank.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;


    public Client(String fName, String lName, String pAddress, Account cAccount,
                  Account sAccount, LocalDate date) {

        this.firstName = new SimpleStringProperty(this, "FirstName", fName);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "Savings Account", sAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", date);
    }

    public StringProperty firstNameProperty() {

        return this.firstName;
    }

    public StringProperty lastNameProperty() {

        return this.lastName;
    }


    public StringProperty pAddressProperty() {

        return this.payeeAddress;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public ObjectProperty<Account> checkingAccountProperty() {

        return this.checkingAccount;
    }

    public ObjectProperty<Account> savingsAccountProperty() {

        return this.savingsAccount;
    }

    public ObjectProperty<LocalDate> dateProperty() {

        return this.dateCreated;
    }
}
