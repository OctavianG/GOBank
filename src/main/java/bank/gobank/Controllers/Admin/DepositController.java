package bank.gobank.Controllers.Admin;

import bank.gobank.Models.Client;
import bank.gobank.Models.Model;
import bank.gobank.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField pAddress_fld;
    public Button search_btn;
    public ListView<Client> result_listview;
    public TextField amount_fld;
    public Button deposit_btn;
    public Label message_lbl;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(event -> onClientSearch());
        deposit_btn.setOnAction(event -> onDeposit());
    }

    private void onClientSearch() {
        ObservableList<Client> searchResults = Model.getInstance().searchClient(pAddress_fld.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.get(0);
    }

    private void onDeposit() {
        double amount = Double.parseDouble(amount_fld.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if (amount_fld.getText() != null) {
            Model.getInstance().getDatabaseDriver().depositSavings(client.pAddressProperty().get(), newBalance);
            message_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
            message_lbl.setText("Client: " + client.savingsAccountProperty().get().ownerProperty().getValue() + " has successfully received: " + amount + " $!");
        }
        emptyFields();
    }

    private void emptyFields() {
        pAddress_fld.setText("");
        amount_fld.setText("");
    }
}