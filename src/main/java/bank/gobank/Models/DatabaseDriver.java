package bank.gobank.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    private Connection conn;

    private DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:gobank.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Client Section */

    /* Admin Section */

    /* Utility Methods */
}
