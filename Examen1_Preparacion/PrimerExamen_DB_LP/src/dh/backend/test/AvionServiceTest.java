package dh.backend.test;

import dh.backend.dao.impl.AvionIDaoH2;
import dh.backend.model.Avion;
import dh.backend.service.AvionService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvionServiceTest {

    private static Logger LOGGER = Logger.getLogger(AvionServiceTest.class);
    private static AvionService avionService = new AvionService(new AvionIDaoH2());

    @BeforeAll
    static  void crearTablas(){

        Connection connection = null;

        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/idaoExamenPrevio;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }finally {
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.error(e.getMessage());
            }
        }

    }

    @Test
    @DisplayName("Testear que un avion persiste en la base de datos")
    void testearAvionEnBaseDeDatos(){
        Avion avion = new Avion("BOEING", "737", "ABE234", LocalDate.of(2023,10,02));
        Avion avionPersistido = avionService.registrarAvion(avion);

        assertNotNull(avionPersistido);
    }

    @Test
    @DisplayName("Testear que retorne un avión pasándole el ID")
    void testearAvionPorId(){

        Integer id = 2;
        Avion avionEncontrado = avionService.buscarPorID(id);
        assertEquals(id, avionEncontrado.getId());

    }

    @Test
    @DisplayName("Testear que elimine un avión pasándole el ID")
    void testearEliminarAvionPorId(){

        Integer id = 2;
        Avion avionEliminado = avionService.eliminarPorId(id);
        assertEquals(id, avionEliminado.getId());
    }

    @Test
    @DisplayName("Testear que arroje null cuando un id no existe")
    void testearNullId(){

        Integer id = 3;
        Avion avionEliminado = avionService.eliminarPorId(id);
        assertNull(avionEliminado);
    }


    @Test
    @DisplayName("Testear que se puedan mostrar todos los aviones")
    void testearTodosLosAviones(){
        List<Avion> avionesRecibidos= avionService.buscarTodos();
        assertEquals(2,avionesRecibidos.size());

    }


}