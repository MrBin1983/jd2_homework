import java.math.BigDecimal;
import java.sql.*;

public class SqlExpensePreparedStatement {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            int num = Integer.parseInt(args[0]);
            String paydate = String.valueOf(args[1]);
            int receiver = Integer.parseInt(args[2]);
            BigDecimal value = new BigDecimal(args[3]);

            try (Connection connDb = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root")) {

                connDb.createStatement().executeUpdate("create database if not exists ListExpenses;");

            } catch (SQLException e) {
                System.out.println("Error create Schema");
            }

            try (Connection connSchema = DriverManager.getConnection("jdbc:mysql://localhost:3306/ListExpenses", "root", "root")) {

                Statement statement = connSchema.createStatement();

                statement.executeUpdate("create table IF NOT EXISTS T_EXPENSES (" +
                        "num int, " +
                        "paydate varchar(255), " +
                        "receiver int, " +
                        "value dec(10,2));");

                PreparedStatement preparedStatement = connSchema.prepareStatement("INSERT INTO T_EXPENSES (num, paydate, receiver, value) Values (?, ?, ?, ?)");
                preparedStatement.setInt(1, num);
                preparedStatement.setString(2, paydate);
                preparedStatement.setInt(3, receiver);
                preparedStatement.setBigDecimal(4, value);

                preparedStatement.executeUpdate();

                ResultSet result = statement.executeQuery("select * from T_EXPENSES");

                while (result.next()) {

                    System.out.println(result.getString(1) + " " +
                            result.getString(2) + " " +
                            result.getString(3) + " " +
                            result.getString(4));
                }

            } catch (SQLException e) {
                System.out.println("Error connection to bd");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver jdbc");
        }
    }
}
