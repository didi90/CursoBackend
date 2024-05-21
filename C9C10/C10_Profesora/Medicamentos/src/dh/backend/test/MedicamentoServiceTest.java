package dh.backend.test;

import dh.backend.dao.impl.MedicamentoIDaoH2;
import dh.backend.model.Medicamento;
import dh.backend.service.MedicamentoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MedicamentoServiceTest {

    @BeforeAll
     static  void crearTablas(){

        Connection connection = null;

        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/C10_medicamentos;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");
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

    public static Logger LOGGER = Logger.getLogger(MedicamentoServiceTest.class);

    @Test
    @DisplayName("Testear que un medicamento persiste en la base de datos")
    void testearMedicamentoEnBaseDeDatos(){
        Medicamento medicamento = new Medicamento(1234, "ASPIRINA", "BAGO", 30, 10);
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoIDaoH2());
        Medicamento medicamentoPersistido = medicamentoService.registrarMedicamento(medicamento);

        assertNotNull(medicamentoPersistido);
    }

    @Test
    @DisplayName("Testear que retorne un medicamento pasándole un nombre")
    void testearMedicamentoPorNombre(){
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoIDaoH2());
        String nombre = "IBUPROFENO";
        Medicamento medicamentoEncontrado = medicamentoService.buscarPorNombre(nombre);
        assertEquals(nombre, medicamentoEncontrado.getNombre());

    }

    @Test
    @DisplayName("Testear que retorne un medicamento pasándole un nombre errado")
    void testearMedicamentoPorNombreErrado(){
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoIDaoH2());
        String nombre = "ACETAMINOFEN";
        Medicamento medicamentoEncontrado = medicamentoService.buscarPorNombre(nombre);
        assertNull(medicamentoEncontrado);

    }

    @Test
    @DisplayName("Testear que se puedan mostrar todos los medicamentos")
    void testearTodosLosMedicamentos(){
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoIDaoH2());
        List<Medicamento> medicamentosRecibidos= medicamentoService.buscarTodos();
        assertEquals(2,medicamentosRecibidos.size());

    }



}