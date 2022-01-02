import Connection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool();
        try (Connection conn = connectionPool.getConnection()) {
            System.out.println("Connection to Store DB succesfull!");
            int salaryBound = 10000;

            String sqlQuery1 = "SELECT * FROM person " +
                    "WHERE salary < ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery1);

            preparedStatement.setInt(1, salaryBound);

            ResultSet resultSet = preparedStatement.executeQuery();
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
