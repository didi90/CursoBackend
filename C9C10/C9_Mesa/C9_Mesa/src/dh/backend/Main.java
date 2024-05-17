package dh.backend;
import org.apache.log4j.Logger;
import dh.backend.db.H2Connection;
import dh.backend.model.Odontologo;

import java.sql.*;


public class Main {
    public static Logger LOGGER = Logger.getLogger(Main.class);

    public static final String SQL_CREATE = "DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS"
            +"("
            + "ID INT AUTO_INCREMENT PRIMARY KEY,"
            + "APELLIDO VARCHAR(50) NOT NULL,"
            + "NOMBRE VARCHAR(50) NOT NULL,"
            + "MATRICULA VARCHAR(50) NOT NULL)";
    public static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    public static final String SQL_SELECT = "SELECT * FROM ODONTOLOGOS";
    public static final String SQL_UPDATE = "UPDATE ODONTOLOGOS SET MATRICULA = ? WHERE APELLIDO=?";

    public static void main(String[] args) throws Exception{

        Connection connection = null;
        Odontologo odontologo = new Odontologo("Ruiz","Diana","1234");
        Odontologo odontologoDesdeDB = null;

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            //Crear tabla
            statement.execute(SQL_CREATE);
            //inserción de datos
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);

            //Empiezo a insertar en la BD, sólo en los signos de ?

            preparedStatement.setString(1,odontologo.getApellido());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.executeUpdate();

            // mostrar los datos desde la base de datos
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                Integer id = resultSet.getInt((1));
                String apellido = resultSet.getString((2));
                String nombre = resultSet.getString((3));
                String matricula = resultSet.getString((4));

                odontologoDesdeDB = new Odontologo(id, apellido, nombre, matricula);
            }
            LOGGER.info("Datos del profesional: "+ odontologoDesdeDB);

            // update
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, "4321");
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.executeUpdate();
            odontologo.setMatricula("4321");

            // mostrar los datos desde la base de datos
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                Integer id = resultSet.getInt((1));
                String apellido = resultSet.getString((2));
                String nombre = resultSet.getString((3));
                String matricula = resultSet.getString((4));

                odontologoDesdeDB = new Odontologo(id, apellido, nombre, matricula);
            }
            LOGGER.info("Datos del profesional: "+ odontologoDesdeDB);


        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();

            }catch(SQLException e){
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }


        }




    }

}
