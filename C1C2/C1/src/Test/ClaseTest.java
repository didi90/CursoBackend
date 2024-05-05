package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//alt enter para importar la librería en el proyecto
public class ClaseTest {
    @Test
    void testearQueSeanIguales(){
        int numero1 = 1;
        int numero2 = 2;

        assertEquals(numero1,numero2);
        //assertNotEquals(numero1,numero2);

    }

    @Test
    void testearQueSeaVerdaderoOFalso(){
        int numero3 = 1;
        int numero4 = 2;

        assertTrue(numero3<numero4);
        assertFalse(numero4<numero3);

    }

}

