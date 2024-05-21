package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.db.H2Connection;
import dh.backend.model.Avion;
import org.apache.log4j.Logger;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvionIDaoH2 implements IDao<Avion> {

    public static Logger LOGGER = Logger.getLogger(AvionIDaoH2.class);
    public static String SQL_INSERT = "INSERT INTO AVIONES VALUES (DEFAULT,?,?,?,?)";
    public static String SQL_SELECT_ID = "SELECT * FROM AVIONES WHERE ID=?";
    public static String SQL_DELETE_ID = "DELETE FROM AVIONES WHERE ID=?";
    public static String SQL_SELECT_ALL = "SELECT * FROM AVIONES";

    @Override
    public Avion registrar(Avion avion) {
        Connection connection = null;
        Avion avionARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, avion.getMarca());
            preparedStatement.setString(2, avion.getModelo());
            preparedStatement.setString(3, avion.getMatricula());
            preparedStatement.setDate(4, Date.valueOf(avion.getFechaEntradaServicio()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                avionARetornar = new Avion(id, avion.getMarca(), avion.getModelo(), avion.getMatricula(),
                        avion.getFechaEntradaServicio());
            }
            LOGGER.info("Avión persistido: "+ avionARetornar);

            connection.commit();
            connection.setAutoCommit(true);

        }catch (Exception e){

            if(connection!= null){
                try{
                    connection.rollback();
                }catch (SQLException ex){
                    LOGGER.error(e.getMessage());
                }
            }

            LOGGER.error(e.getMessage());
            e.printStackTrace();

        }finally {
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return avionARetornar;
    }

    @Override
    public Avion buscarPorID(Integer id) {
        Connection connection = null;
        Avion avionEncontrado = null;

        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1,id);

            //para retornar y mostrar
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer idNuevo = resultSet.getInt(1);
                String modelo = resultSet.getString(2);
                String marca = resultSet.getString(3);
                String matricula = resultSet.getString(4);
                LocalDate fechaEntradaServicio = resultSet.getDate(5).toLocalDate();
                avionEncontrado = new Avion(idNuevo, modelo, marca, matricula, fechaEntradaServicio);
            }
            LOGGER.info("El avión encontrado es: "+ avionEncontrado);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return avionEncontrado;
    }

    @Override
    public Avion eliminarPorID(Integer id) {
        Connection connection = null;
        Avion avionAEliminar = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            // Obtener el avión antes de eliminarlo
            //String selectSQL = "SELECT * FROM aviones WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String matricula = resultSet.getString("matricula");
                LocalDate fechaEntradaServicio = resultSet.getDate("fechaEntradaServicio").toLocalDate();

                avionAEliminar = new Avion(id, marca, modelo, matricula, fechaEntradaServicio);
            } else {
                // Si no se encuentra el avión, retorna null
                return null;
            }

            // Eliminar el avión
            String deleteSQL = "DELETE FROM aviones WHERE id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();

            LOGGER.info("Avión eliminado: " + avionAEliminar);

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return avionAEliminar;
    }

    @Override
    public List<Avion> buscarTodos() {
        Connection connection = null;
        Avion avion = null;
        List<Avion> aviones = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while(resultSet.next()){
                avion = new Avion(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate());
                LOGGER.info("El avión devuelto es: "+ avion);
                aviones.add(avion);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return aviones;
    }
}
