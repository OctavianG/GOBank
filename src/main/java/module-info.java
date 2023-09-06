module bank.gobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;



    opens bank.gobank to javafx.fxml;
    exports bank.gobank;
    exports bank.gobank.Controllers;
    exports bank.gobank.Controllers.Admin;
    exports bank.gobank.Controllers.Client;
    exports bank.gobank.Models;
    exports bank.gobank.Views;
}