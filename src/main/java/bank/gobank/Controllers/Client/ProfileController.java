package bank.gobank.Controllers.Client;

import bank.gobank.Models.CheckingAccount;
import bank.gobank.Models.Client;
import bank.gobank.Models.Model;
import bank.gobank.Models.SavingsAccount;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private static ProfileController profileController;
    public Label firstName_lbl;
    public Label lastName_lbl;
    public Label pAddress_lbl;
    public Label password_lbl;
    public Label ch_acc_num;
    public Label ch_acc_balance;
    public Label sv_acc_num;
    public Label sv_acc_balance;
    public TextField change_firstName_txt;
    public TextField change_lastName_txt;
    public TextField change_password_txt;
    public Button makeChanges_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bindProfileData();
        makeChanges_btn.setOnAction(event -> handleMakeChangesButton());

    }

    public void bindProfileData() {
        Client client = Model.getInstance().getClient();
        String pAddress = Model.getInstance().getClient().pAddressProperty().get();
        CheckingAccount checkingAccount = Model.getInstance().getCheckingAccount(pAddress);
        SavingsAccount savingsAccount = Model.getInstance().getSavingsAccount(pAddress);

        // Personal Information
        //firstName_lbl.textProperty().bind(client.firstNameProperty());
        firstName_lbl.textProperty().bind(client.firstNameProperty());
        lastName_lbl.textProperty().bind(client.lastNameProperty());
        pAddress_lbl.textProperty().bind(client.pAddressProperty());
        String password = Model.getInstance().getDatabaseDriver().getClientPassword(pAddress);
        password_lbl.setText(password);

        // Accounts Information
        ch_acc_num.textProperty().bind(client.checkingAccountProperty().asString());
        ch_acc_balance.textProperty().bind(checkingAccount.balanceProperty().asString());
        sv_acc_num.textProperty().bind(client.savingsAccountProperty().asString());
        sv_acc_balance.textProperty().bind(savingsAccount.balanceProperty().asString());
    }

    public static synchronized ProfileController getInstance() {
        if (profileController == null) {
            profileController = new ProfileController();
        }

        return profileController;
    }

    @FXML
    public void handleMakeChangesButton() {
        String newFirstName = change_firstName_txt.getText();
        String newLastName = change_lastName_txt.getText();
        String newPassword = change_password_txt.getText();

        String pAddress = Model.getInstance().getClient().pAddressProperty().get();
        Client client = Model.getInstance().getClient();

        if (!newFirstName.isEmpty()) {
            // Update the firstName in the database
            Model.getInstance().getDatabaseDriver().updateClientFirstName(Model.getInstance().getClient().
                    pAddressProperty().get(), newFirstName);
            firstName_lbl.textProperty().unbind();
            firstName_lbl.setText(newFirstName);
        }

        if (!newLastName.isEmpty()) {
            // Update the lastName in the database
            Model.getInstance().getDatabaseDriver().updateClientLastName(Model.getInstance().getClient()
                    .pAddressProperty().get(), newLastName);
            lastName_lbl.textProperty().unbind();
            lastName_lbl.setText(newLastName);
        }


        if (!newPassword.isEmpty()) {
            // Update the password in the database
            Model.getInstance().getDatabaseDriver().updateClientPassword(Model.getInstance().getClient()
                    .pAddressProperty().get(), newPassword);
            String password = Model.getInstance().getDatabaseDriver().getClientPassword(pAddress);
            password_lbl.setText(password);
        }

        // Clear the text fields
        change_firstName_txt.clear();
        change_lastName_txt.clear();
        change_password_txt.clear();
    }
}



