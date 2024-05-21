package dh.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        //cambiar siempre nombre
        return DriverManager.getConnection("jdbc:h2:~/C10_medicamentos","sa","sa");
    }

}




