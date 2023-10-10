package bank.gobank.Controllers.Client;

import bank.gobank.Models.CheckingAccount;
import bank.gobank.Models.Client;
import bank.gobank.Models.Model;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
    }

    private void bindData() {
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
}
