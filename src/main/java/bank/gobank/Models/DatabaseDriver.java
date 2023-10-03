package bank.gobank.Models;

import java.sql.*;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:gobank.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Client Section */

    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='"+pAddress+"' AND Password='"+password+"';");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    /* Admin Section */

    /* Utility Methods */
}
