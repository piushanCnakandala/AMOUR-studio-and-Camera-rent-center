package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

        private static db.DbConnection dbConnection=null;
        private Connection connection;


        private DbConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AMORE","root","12345");
        }
        public  static db.DbConnection getInstance() throws SQLException, ClassNotFoundException {
            if (dbConnection==null) dbConnection=new db.DbConnection();
            return  dbConnection;

        }
        public Connection getConnection(){
            return  connection;

        }


}
