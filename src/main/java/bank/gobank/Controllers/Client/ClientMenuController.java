package bank.gobank.Controllers.Client;

import bank.gobank.Models.CheckingAccount;
import bank.gobank.Models.Model;
import bank.gobank.Models.SavingsAccount;
import bank.gobank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;
    public CheckingAccount checkingAccount;
    public SavingsAccount savingsAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(events -> onAccounts());
        profile_btn.setOnAction(event -> onProfile());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
        
    }


    private void onLogout() {
        // Get Stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        // Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Client login SuccessFlag to false
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}
