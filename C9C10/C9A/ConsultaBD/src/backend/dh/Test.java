package backend.dh;

//import org.apache.log4j.Logger;

import java.sql.*;

public class Test {
  //  private static final Logger LOGGER = Logger.getLogger(Test.class);
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS PACIENTES; CREATE TABLE PACIENTES"
            +"("
            + "ID INT PRIMARY KEY,"
            + "NOMBRE VARCHAR(50) NOT NULL,"
            + "APELLIDO VARCHAR(50) NOT NULL,"
            + "DOMICILIO VARCHAR(50) NOT NULL,"
            + "DNI INT NOT NULL,"
            + "FECHADEALTA VARCHAR(50) NOT NULL,"
            + "USUARIO VARCHAR(50) NOT NULL,"
            + "PASSWORD VARCHAR(50) NOT NULL)";
    private static final String SQL_INSERT = "INSERT INTO PACIENTES  (ID, NOMBRE, APELLIDO, DOMICILIO, DNI, FECHADEALTA, USUARIO, PASSWORD) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE = "UPDATE PACIENTES SET PASSWORD = ? WHERE NOMBRE=?";


    public static void main(String[] args) throws Exception{
        Paciente paciente = new Paciente("Diana", "Bolanios", "Itagui",1144, "2024-05-13","dianaB","1234");

        Connection connection = null;

        try{
            connection = getConnection();
            Statement statement = connection.createStatement();
            // creamos la tabla
            statement.execute(SQL_TABLE_CREATE);
            // insertar datos en la tabla

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);

            //Empiezo a insertar en la BD

            psInsert.setInt(1,1);
            psInsert.setString(2, paciente.getNombre());
            psInsert.setString(3, paciente.getApellido());
            psInsert.setString(4, paciente.getDomicilio());
            psInsert.setInt(5, paciente.getDNI());
            psInsert.setString(6, paciente.getFechaDeAlta());
            psInsert.setString(7, paciente.getUsuario());
            psInsert.setString(8, paciente.getPassword());

            psInsert.execute();

           //LOGGER.info("------------------------------------------------------------");

            //Empezar la transacción
            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"4321");
            psUpdate.setString(2, paciente.getNombre());
            psUpdate.execute();

            int a = 4/0;

            connection.commit();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM PACIENTES";
            Statement stmt = connection.createStatement();
            ResultSet rd = stmt.executeQuery(sql);

            while(rd.next()){
                System.out.println(rd.getInt(1)+rd.getString(2)+rd.getString(3)+rd.getString(4)+rd.getInt(5)+rd.getString(6)+rd.getString(7)+rd.getString(8));
            }



        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
           // LOGGER.error(e.getMessage());

        } finally {
            connection.close();
        }
        Connection connection1 = getConnection();
        String sql = "SELECT * FROM PACIENTES";
        Statement stmt = connection1.createStatement();
        ResultSet rd = stmt.executeQuery(sql);
        while(rd.next()){
            System.out.println(rd.getInt(1)+rd.getString(2)+rd.getString(3)+rd.getString(4)+rd.getInt(5)+rd.getString(6)+rd.getString(7)+rd.getString(8));
        }

        }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase10","","");
    }

}
