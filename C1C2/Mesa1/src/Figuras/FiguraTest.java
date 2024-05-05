package Figuras;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FiguraTest {
    @Test
    void chequearValoresCeroCuadrado(){
        //cuando
        Figura cuadrado = new Cuadrado(0);
        // act
        String esperado = "El valor del lado debe ser mayor que cero";

        assertEquals(esperado, cuadrado.area());
    }

    @Test
    void chequearValoresCeroCirculo(){
        //cuando
        Figura circulo = new Circulo(0);
        // act
        String esperado = "El valor del radio debe ser mayor que cero";

        assertEquals(esperado, circulo.area());
    }

    @Test
    void chequearAreaCuadrado(){
        //dado
        Figura cuadrado = new Cuadrado(10);
        // cuando
        String esperado = "El área del cuadrado es 100.0 unidades";
        // entonces
        assertEquals(esperado, cuadrado.area());
    }


}