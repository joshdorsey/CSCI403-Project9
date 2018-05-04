package Data;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class DatabaseManager {
    public static Connection connect() {
        Connection db = null;
        try {
            Class.forName("org.postgresql.Driver");
            String connectString = "jdbc:postgresql://flowers.mines.edu/csci403";
            String username;
            String password;
            if(System.console() == null){
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Username: ");
                username = keyboard.nextLine();
                System.out.print("Password: ");
                password = keyboard.nextLine();
            } else {
                System.out.print("Username: ");
                username = System.console().readLine();
                System.out.print("Password: ");
                password = new String(System.console().readPassword());
            }

            db = DriverManager.getConnection(connectString, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to database: " + e);
            System.exit(1);
        }
        return db;
    }

    public static ResultSet runQuery(Connection db, String q) {
        ResultSet results = null;
        try {
            PreparedStatement statement = db.prepareStatement(q);
            results = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
        }
        return results;
    }
}