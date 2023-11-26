import java.math.BigDecimal;
import java.sql.*;

public class SqlExpense {

    public static void main(String[] args) {

        int id = Integer.parseInt(args[0]);
        String paydate = String.valueOf(args[1]);
        int receiver = Integer.parseInt(args[2]);
        BigDecimal value = new BigDecimal(args[3]);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver jdbc");
        }

        try {
            Connection connSchema = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

            connSchema.createStatement().executeUpdate("create database if not exists ListExpenses;");

            connSchema.close();

            Connection connDb = DriverManager.getConnection("jdbc:mysql://localhost:3306/ListExpenses", "root", "root");

            Statement statement = connDb.createStatement();

            statement.executeUpdate("create table IF NOT EXISTS T_EXPENSES (" +
                    "num int, " +
                    "paydate varchar(255), " +
                    "receiver int, " +
                    "value dec(10,2));");

            statement.executeUpdate("insert into T_EXPENSES values (" +
                    id + ", '" +
                    paydate + "', " +
                    receiver + ", " +
                    value + ");");

            ResultSet result = statement.executeQuery("select * from T_EXPENSES");

            while (result.next()) {

                System.out.println(result.getString(1) + " " +
                        result.getString(2) + " " +
                        result.getString(3) + " " +
                        result.getString(4));
            }

            connDb.close();

        } catch (SQLException e) {
            System.out.println("Error connection to bd");
        }
    }
}
