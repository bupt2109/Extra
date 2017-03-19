import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * class for SQL <br>
 * JDBC connection and sql  <br>
 */
public class PortfolioSQL {
    //JDCB connection
    private static Connection conn = getConn();

    /**
     * initial JDBC connection
     */
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";                //JDBC driver class
        String url = "jdbc:mysql://localhost:3306/portfolios";  //替换成你需要的 JDBC database
        String username = "root";                               //替换成你需要的 JDBC user name
        String password = "123456";                             //替换成你需要的 JDBC password
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader for JDBC
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *  get JDBC connection
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * select query with certain sql statement
     * @param sql sql statement
     * @return ResultSet
     */
    public static ResultSet query(String sql) {
        PreparedStatement stmt;
        try {
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
