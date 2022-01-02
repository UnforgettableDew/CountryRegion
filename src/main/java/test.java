import Connection.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool();
        try (Connection conn = connectionPool.getConnection()) {
            System.out.println("Connection to Store DB succesfull!");
            String sqlQuery = "SELECT * FROM person " +
                    "WHERE salary<10000";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                int salary = resultSet.getInt("salary");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + " | " + name + " | " + salary);
            }

            int countOfUpdatedRows = statement.executeUpdate(
                    "update person " +
                    "set salary = 6788 " +
                    "where id = 6");

            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                int salary = resultSet.getInt("salary");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + " | " + name + " | " + salary);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }


    }
}
