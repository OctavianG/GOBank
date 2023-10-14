package bank.gobank.Controllers.Client;


import bank.gobank.Models.Client;
import bank.gobank.Models.Model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private static ProfileController profileController;
    @FXML
    public Label firstName_lbl;
    @FXML
    public Label lastName_lbl;
    @FXML
    public Label pAddress_lbl;
    @FXML
    public Label password_lbl;
    @FXML
    public Label ch_acc_num;
    @FXML
    public Label ch_acc_balance;
    @FXML
    public Label sv_acc_num;
    @FXML
    public Label sv_acc_balance;
    @FXML
    public TextField change_firstName_txt;
    public TextField change_lastName_txt;
    public TextField change_password_txt;
    public Button makeChanges_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        makeChanges_btn.setOnAction(event -> handleMakeChangesButton());
        bindProfileData();
    }

    public void bindProfileData() {
        String pAddress = Model.getInstance().getClient().pAddressProperty().getValue();

        // Personal Information
        firstName_lbl.textProperty().bind(Model.getInstance().getClient().firstNameProperty());
        lastName_lbl.textProperty().bind(Model.getInstance().getClient().lastNameProperty());
        pAddress_lbl.textProperty().bind(Model.getInstance().getClient().pAddressProperty());
        String password = Model.getInstance().getDatabaseDriver().getClientPassword(pAddress);
        password_lbl.setText(password);

        // Accounts Information
        ch_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().asString());
        ch_acc_balance.textProperty().bind(Model.getInstance().getCheckingAccount(pAddress).balanceProperty().asString());
        sv_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().asString());
        sv_acc_balance.textProperty().bind(Model.getInstance().getSavingsAccount(pAddress).balanceProperty().asString());
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

        ch_acc_balance.textProperty().bind(Model.getInstance().getCheckingAccount(pAddress).balanceProperty().asString());
        sv_acc_balance.textProperty().bind(Model.getInstance().getSavingsAccount(pAddress).balanceProperty().asString());
    }
}



