package dh.backend.clinicamvc.dao.impl;
import dh.backend.clinicamvc.dao.IDao;
import dh.backend.clinicamvc.db.H2Connection;
import dh.backend.clinicamvc.model.Domicilio;
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoIDaoH2 implements IDao<Odontologo>  {

    public static Logger LOGGER = LoggerFactory.getLogger(OdontologoIDaoH2.class);
    public static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    public static String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static String SQL_SELECT_ID = "SELECT * FROM ODONTOLOGOS WHERE ID=?";
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(),
                        odontologo.getApellido());
            }
            LOGGER.info("Odontólogo persistido: "+ odontologoARetornar);

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

        return odontologoARetornar;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer idDevuelto = resultSet.getInt(1);
                String matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologoEncontrado = new Odontologo(idDevuelto, matricula, nombre,apellido);
            }
            LOGGER.info("odontologo encontrado: "+ odontologoEncontrado);

        }catch (Exception e){
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoEncontrado;

    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while(resultSet.next()){
                odontologo = new Odontologo(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4));
                LOGGER.info("El odontólogo devuelto es: "+ odontologo);
                odontologos.add(odontologo);
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
        return odontologos;
    }






}
