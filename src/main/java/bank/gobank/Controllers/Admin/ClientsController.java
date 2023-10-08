package bank.gobank.Controllers.Admin;

import bank.gobank.Models.Client;
import bank.gobank.Models.Model;
import bank.gobank.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> clients_listview;
    private static ClientsController clientsController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getClients());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    public static synchronized ClientsController getInstance() {
        if (clientsController == null) {
            clientsController = new ClientsController();
        }

        return clientsController;
    }

    private void initClientsList() {
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}


