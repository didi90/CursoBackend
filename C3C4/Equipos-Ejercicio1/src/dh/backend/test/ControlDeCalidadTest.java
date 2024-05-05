package dh.backend.test;
import dh.backend.model.Articulo;
import dh.backend.service.AnalistaDeCalidad;
import dh.backend.service.ControlDeCalidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControlDeCalidadTest {

    @Test
    @DisplayName("Control de Calidad Exitoso")
    void test1(){
        Articulo articulo1 = new Articulo("sano", 1200, 1000, "Leche");

        AnalistaDeCalidad analistaDeCalidad = new AnalistaDeCalidad();

        String respuesta = analistaDeCalidad.cadenaCalidad(articulo1);

        assertEquals("Cumple con todos los requisitos. Fue ACEPTADO",respuesta);

    }

    @Test
    @DisplayName("Control de Calidad No exitoso")
    void test2(){
        Articulo articulo2 = new Articulo("hola", 1200, 1000, "Leche");

        AnalistaDeCalidad analistaDeCalidad = new AnalistaDeCalidad();

        String respuesta2 = analistaDeCalidad.cadenaCalidad(articulo2);

        assertEquals("No cumple con ninguno de los requisitos",respuesta2);
    }

}