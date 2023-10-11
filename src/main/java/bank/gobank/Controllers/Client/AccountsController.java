package bank.gobank.Controllers.Client;

import bank.gobank.Models.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label ch_acc_num;
    public Label transactions_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_cv_btn;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindAccountData();
        trans_to_sv_btn.setOnAction(event -> transferToSavingsAccount());
        trans_to_cv_btn.setOnAction(event -> transferToCheckingAccount());
    }

    public void bindAccountData() {
        String pAddress = Model.getInstance().getClient().pAddressProperty().getValue();
        ch_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        transactions_limit.textProperty().bind(Model.getInstance().getCheckingAccount(pAddress).transactionLimitProp().asString());
        ch_acc_date.textProperty().bind(Model.getInstance().getClient().dateProperty().asString());
        ch_acc_bal.textProperty().bind(Model.getInstance().getCheckingAccount(pAddress).balanceProperty().asString());
        sv_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
        withdrawal_limit.textProperty().bind(Model.getInstance().getSavingsAccount(pAddress).withdrawalLimitProperty().asString());
        sv_acc_date.textProperty().bind(Model.getInstance().getClient().dateProperty().asString());
        sv_acc_bal.textProperty().bind(Model.getInstance().getSavingsAccount(pAddress).balanceProperty().asString());
    }

    private void transferToSavingsAccount() {
        try {
            String pAddress = Model.getInstance().getClient().pAddressProperty().get();
            double amount = Double.parseDouble(amount_to_sv.getText());
            Client client = Model.getInstance().getClient();
            CheckingAccount checkingAccount = Model.getInstance().getCheckingAccount(pAddress);
            SavingsAccount savingsAccount = Model.getInstance().getSavingsAccount(pAddress);

            // Calculate new balances
            double newBalanceChecking = checkingAccount.balanceProperty().doubleValue() - amount;
            double newBalanceSavings = client.savingsAccountProperty().get().balanceProperty().doubleValue() + amount;

            // Check if there are sufficient funds in the checking account
            if (newBalanceChecking >= 0) {
                // Perform the transfer
                Model.getInstance().getDatabaseDriver().updateBalanceSavings(pAddress, amount, "ADD");
                Model.getInstance().getDatabaseDriver().updateBalanceChecking(pAddress, amount, "SUB");

                bindAccountData();

                // Display a success message
                error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
                error_lbl.setText("Transferring " + amount + " $ to Savings Account");


            } else {
                // Display an error message for insufficient funds
                error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
                error_lbl.setText("Insufficient funds in Checking Account");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update the account data and clear input fields

        clearFields();
    }

    private void transferToCheckingAccount() {
        try {
            String pAddress = Model.getInstance().getClient().pAddressProperty().get();
            double amount = Double.parseDouble(amount_to_ch.getText());
            Client client = Model.getInstance().getClient();
            CheckingAccount checkingAccount = Model.getInstance().getCheckingAccount(pAddress);
            SavingsAccount savingsAccount = Model.getInstance().getSavingsAccount(pAddress);

            // Calculate new balances
            double newBalanceChecking = checkingAccount.balanceProperty().doubleValue() + amount;
            double newBalanceSavings = savingsAccount.balanceProperty().doubleValue() - amount;

            // Check if there are sufficient funds in the savings account
            if (newBalanceSavings >= 0) {
                // Perform the transfer
                Model.getInstance().getDatabaseDriver().updateBalanceChecking(pAddress, amount, "ADD");
                Model.getInstance().getDatabaseDriver().updateBalanceSavings(pAddress, amount, "SUB");

                bindAccountData();

                // Display a success message
                error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
                error_lbl.setText("Transferring " + amount + " $ to Checking Account");
            } else {
                // Display an error message for insufficient funds
                error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold");
                error_lbl.setText("Insufficient funds in Savings Account");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update the account data and clear input fields
        clearFields();
    }

    private void clearFields() {
        amount_to_sv.setText("");
        amount_to_ch.setText("");
    }
}
