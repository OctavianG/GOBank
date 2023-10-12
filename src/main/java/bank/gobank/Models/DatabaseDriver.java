package bank.gobank.Models;

import javafx.collections.FXCollections;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:gobank.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*

    Client Section

    */

    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='" + pAddress + "' AND Password='" + password + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public String getClientPassword(String pAddress) {
        Statement statement;
        ResultSet resultSet;
        String pass = "";
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT Password FROM CLIENTS WHERE PAYEEADDRESS='"+pAddress+"';");
            if (resultSet.next()) {
                pass = resultSet.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pass;
    }

    public ResultSet getTransactions(String pAddress, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender='"+pAddress+"' OR Receiver='"+pAddress+"' LIMIT "+limit+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    // Method returns savings account Balance
    public double getSavingsAccountBalance(String pAddress) {
        Statement statement;
        ResultSet resultSet;
        double balance = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
            balance = resultSet.getDouble("Balance");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }




    // Method to add or subtract from Balance
    public void updateBalance(String pAddress, double amount, String operation) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
            double newBalance = 0;
            if (operation.equals("ADD")) {
                 newBalance = resultSet.getDouble("Balance") + amount;
                 statement.executeUpdate("UPDATE SavingsAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
            } else {
                if (resultSet.getDouble("Balance") >= amount) {
                     newBalance = resultSet.getDouble("Balance") - amount;
                     statement.executeUpdate("UPDATE SavingsAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalanceChecking (String pAddress, double amount, String operation) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
            double newBalance = 0;
            if (operation.equals("ADD")) {
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE CheckingAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
            } else {
                if (resultSet.getDouble("Balance") >= amount) {
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE CheckingAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalanceSavings (String pAddress, double amount, String operation) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
            double newBalance = 0;
            if (operation.equals("ADD")) {
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE SavingsAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
            } else {
                if (resultSet.getDouble("Balance") >= amount) {
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE SavingsAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Creates and records new transaction
    public void newTransaction(String sender, String receiver, double amount, String message) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO " +
                    "Transactions(Sender, Receiver, Amount, Date, Message)" +
                    "VALUES ('"+sender+"', '"+receiver+"', "+amount+", '"+date+"', '"+message+"');");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClientPassword(String pAddress, String newPassword) {
        Statement statement;
        ResultSet resultSet;
        try  {
            statement = this.conn.createStatement();
            statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='"+pAddress+"';");
            statement.executeUpdate("UPDATE Clients SET Password="+newPassword+" WHERE PayeeAddress='"+pAddress+"';");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    /*

    Admin Section


     */

    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admins WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Clients(FirstName, LastName, PayeeAddress, Password, Date)" +
                    "VALUES ('"+fName+"', '"+lName+"', '"+pAddress+"', '"+password+"', '"+date.toString()+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "CheckingAccounts(Owner, AccountNumber, TransactionLimit, Balance)" +
                    "VALUES ('"+owner+"', '"+number+"', "+tLimit+", "+balance+")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSavingsAccount(String owner, String number, double wLimit, double balance) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "SavingsAccounts(Owner, AccountNumber, WithdrawalLimit, Balance)" +
                    "VALUES ('"+owner+"', '"+number+"', "+wLimit+", "+balance+")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM  Clients;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }



    public void depositSavings(String pAddress, double amount) {
        try (PreparedStatement preparedStatement = this.conn.prepareStatement("UPDATE SavingsAccounts SET Balance =  ? WHERE Owner = ?")) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, pAddress);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void depositChecking(String pAddress, double amount) {
        try (PreparedStatement preparedStatement = this.conn.prepareStatement("UPDATE CheckingAccounts SET Balance =  ? WHERE Owner = ?")) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, pAddress);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    /* Utility Methods */

    public ResultSet searchClient(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM Clients WHERE PayeeAddress='"+pAddress+"';");
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public int getLastClientsId() {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Clients';");
            id = resultSet.getInt("seq");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public ResultSet getCheckingAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getSavingsAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void deleteClient(String pAddress) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("DELETE FROM Clients WHERE PayeeAddress='"+pAddress+"';");
            statement.executeUpdate("DELETE FROM CheckingAccounts WHERE Owner='"+pAddress+"';");
            statement.executeUpdate("DELETE FROM SavingsAccounts WHERE Owner='"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return this.conn;
    }
}

