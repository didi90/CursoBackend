package dh.backend;
import org.apache.log4j.Logger;
import dh.backend.db.H2Connection;
import dh.backend.model.Cuenta;

import java.sql.*;


public class Main {
    public static Logger LOGGER = Logger.getLogger(Main.class);

    public static final String SQL_CREATE = "DROP TABLE IF EXISTS CUENTAS; CREATE TABLE CUENTAS"
            +"("
            + "ID INT AUTO_INCREMENT PRIMARY KEY,"
            + "NROCUENTA INT NOT NULL,"
            + "NOMBRE VARCHAR(50) NOT NULL,"
            + "SALDO NUMERIC(7,2) NOT NULL)";
    public static final String SQL_INSERT = "INSERT INTO CUENTAS VALUES (DEFAULT,?,?,?)";
    public static final String SQL_SELECT = "SELECT * FROM CUENTAS";
    public static final String SQL_UPDATE = "UPDATE CUENTAS SET SALDO = ? WHERE NROCUENTA=?";

    public static void main(String[] args) throws Exception{

        Connection connection = null;
        Cuenta cuenta = new Cuenta(1234,"cuenta.np",1000.00);
        Cuenta cuentaDesdeDB = null;

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            //Crear tabla
            statement.execute(SQL_CREATE);
            //inserción de datos
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);

            //Empiezo a insertar en la BD, sólo en los signos de ?

            preparedStatement.setInt(1,cuenta.getNroCuenta());
            preparedStatement.setString(2, cuenta.getNombre());
            preparedStatement.setDouble(3, cuenta.getSaldo());
            preparedStatement.executeUpdate();

            // mostrar los datos desde la base de datos
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                Integer id = resultSet.getInt((1));
                Integer nroCuenta = resultSet.getInt((2));
                String nombre = resultSet.getString((3));
                double saldo = resultSet.getDouble((4));

                cuentaDesdeDB = new Cuenta(id, nroCuenta, nombre, saldo);
            }
            LOGGER.info("Datos de la cuenta: "+ cuentaDesdeDB);

            // update
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setDouble(1, cuenta.getSaldo()+10);
            preparedStatement.setInt(2, cuenta.getNroCuenta());
            preparedStatement.executeUpdate();
            cuenta.setSaldo(cuenta.getSaldo()+10);

            // mostrar los datos desde la base de datos
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                Integer id = resultSet.getInt((1));
                Integer nroCuenta = resultSet.getInt((2));
                String nombre = resultSet.getString((3));
                double saldo = resultSet.getDouble((4));

                cuentaDesdeDB = new Cuenta(id, nroCuenta, nombre, saldo);
            }
            LOGGER.info("Datos de la cuenta: "+ cuentaDesdeDB);

            //Empezar la transacción
            connection.setAutoCommit(false);

      try{
          PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_UPDATE);
          preparedStatement1.setDouble(1, cuenta.getSaldo()+15);
          preparedStatement1.setInt(2, cuenta.getNroCuenta());
          preparedStatement1.executeUpdate();
          cuenta.setSaldo(cuenta.getSaldo()+15);

          int num = 10/0;

          connection.commit();
      }catch (Exception e){
          connection.rollback();

            }
      connection.setAutoCommit(true);

            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()){
                Integer id = resultSet.getInt((1));
                Integer nroCuenta = resultSet.getInt((2));
                String nombre = resultSet.getString((3));
                double saldo = resultSet.getDouble((4));

                cuentaDesdeDB = new Cuenta(id, nroCuenta, nombre, saldo);
            }


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
