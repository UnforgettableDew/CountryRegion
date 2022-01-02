package Connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ConnectionPool {
    private DataSource dataSource;

    public ConnectionPool() {
        try {
            ResourceBundle dbConfig = ResourceBundle.getBundle("credits");
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dbConfig.getString("db.connection.url"));
            basicDataSource.setDriverClassName(dbConfig.getString("db.connection.driver"));
            basicDataSource.setUsername(dbConfig.getString("db.connection.username"));
            basicDataSource.setPassword(dbConfig.getString("db.connection.password"));
            dataSource = basicDataSource;
        } catch (Exception e) {
            System.out.println("Error connection " + Arrays.toString(e.getStackTrace()));
        }
    }

    public Connection getConnection() throws Exception {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error connection " + Arrays.toString(e.getStackTrace()));
            throw new Exception();
        }
    }
}
