package dh.backend.test;
import dh.backend.dao.impl.AvionIDaoH2;
import dh.backend.dao.impl.AvionEnMemoria;
import dh.backend.model.Avion;
import dh.backend.service.AvionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvionEnMemoriaTest {
    private static AvionService avionService = new AvionService(new AvionEnMemoria());

    @Test
    @DisplayName("Testear que un avion fue guardado")
    void testAvionGuardado(){
        Avion avion = new Avion("BOEING", "737", "ABE234", LocalDate.of(2023,10,02));
        Avion avionDesdeLaMemoria = avionService.registrarAvion(avion);

        assertNotNull(avionDesdeLaMemoria);
    }

    @Test
    @DisplayName("Testear busqueda avion por id")
    void t(){
        Avion avion = new Avion("AIRBUS", "839", "ABE234", LocalDate.of(2023,10,02));
        avionService.registrarAvion(avion);

        Integer id = 1;
        Avion avionEncontrado = avionService.buscarPorID(id);

        assertEquals(id, avionEncontrado.getId());
    }

    @Test
    @DisplayName("Testear eliminar avion por id")
    void t2(){
        Avion avion = new Avion("BOEING", "737", "ABE234", LocalDate.of(2023,10,02));
        Avion avion2 = new Avion("AIRBUS", "123", "ABE234", LocalDate.of(2023,10,02));
        Avion avion3 = new Avion("BOEING", "888", "ABE234", LocalDate.of(2023,10,02));
        avionService.registrarAvion(avion);
        avionService.registrarAvion(avion2);
        avionService.registrarAvion(avion3);
        Integer id=2;
        Avion avionEliminado = avionService.eliminarPorId(id);

        assertEquals(id, avionEliminado.getId());
    }


    @Test
    @DisplayName("Testear busqueda todos los aviones")
    void testBusquedaTodos() {
        Avion avion = new Avion("BOEING", "737", "ABE234", LocalDate.of(2023,10,02));
        Avion avion2 = new Avion("AIRBUS", "123", "ABE234", LocalDate.of(2023,10,02));
        Avion avion3 = new Avion("BOEING", "888", "ABE234", LocalDate.of(2023,10,02));
        avionService.registrarAvion(avion);
        avionService.registrarAvion(avion2);
        avionService.registrarAvion(avion3);

        List<Avion> aviones = avionService.buscarTodos();

        assertEquals(3, aviones.size());

    }
}
