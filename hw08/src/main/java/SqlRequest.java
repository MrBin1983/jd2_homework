import java.sql.*;

public class SqlRequest {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

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
                        "count dec(10,2));");

                statement.executeUpdate(
                        "insert into T_EXPENSES values (1, '2023-10-10', 1, 100.00)," +
                                " (2, '2023-10-10', 2, 200.00)," +
                                " (3, '2023-10-10', 3, 300.00)," +
                                " (4, '2023-10-10', 4, 100.00)," +
                                " (5, '2023-10-11', 1, 100.00)," +
                                " (6, '2023-10-11', 2, 400.00)," +
                                " (7, '2023-10-12', 1, 500.00)," +
                                " (8, '2023-10-13', 1, 700.00)," +
                                " (9, '2023-10-14', 1, 100.00)," +
                                " (10, '2023-10-14', 3, 900.00);");

                ResultSet result1 = statement.executeQuery("select receiver, sum(count) from T_EXPENSES" +
                        " group by receiver;");

                while (result1.next()) {
                    System.out.println(result1.getString(1) + " " +
                            result1.getString(2));
                }

                ResultSet result2 = statement.executeQuery("select sum(count) from T_EXPENSES" +
                        " where paydate=(select paydate from T_EXPENSES where count=" +
                        " (SELECT max(count) FROM T_EXPENSES));");

                while (result2.next()) {
                    System.out.println(result2.getString(1));
                }

                ResultSet result3 = statement.executeQuery("select max(count) from T_EXPENSES" +
                        " where paydate =" +
                        " (select paydate from T_EXPENSES" +
                        " group by paydate" +
                        " order by sum(count) desc" +
                        " limit 1);");

                while (result3.next()) {
                    System.out.println(result3.getString(1));
                }

            } catch (SQLException e) {
                System.out.println("Error connection to bd");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver jdbc");

        }
    }
}
