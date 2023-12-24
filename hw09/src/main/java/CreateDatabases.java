import java.sql.*;

public class CreateDatabases {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306",
                    "root",
                    "root")) {

                connDb.createStatement().executeUpdate("create database if not exists ListExpenses;");

            } catch (SQLException e) {
                System.out.println("Error create Schema");
            }

            try (Connection connSchema = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ListExpenses",
                    "root",
                    "root")) {

                Statement statement = connSchema.createStatement();

                statement.executeUpdate("create table IF NOT EXISTS T_EXPENSES (" +
                        "id int, " +
                        "paydate varchar(255), " +
                        "count dec(10,2));");

                statement.executeUpdate(
                        "insert into T_EXPENSES values (1, '2023-10-10', 100.00)," +
                                " (2, '2023-10-10', 200.00)," +
                                " (3, '2023-10-10', 300.00)," +
                                " (4, '2023-10-10', 100.00)," +
                                " (5, '2023-10-11', 100.00);");

                statement.executeUpdate("create table IF NOT EXISTS T_RECEIVER (" +
                        "num int, " +
                        "name varchar(255);");

                statement.executeUpdate(
                        "insert into T_EXPENSES values (1, 'IVAN')," +
                                " (2, 'JOHN')," +
                                " (3, 'NICK')," +
                                " (10, 'LENA');");

            } catch (SQLException e) {
                System.out.println("Error connection to bd");
            }
        } catch (
                ClassNotFoundException e) {
            System.out.println("Error loading driver jdbc");
        }
    }
}
