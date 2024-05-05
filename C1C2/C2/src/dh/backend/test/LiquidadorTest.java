package dh.backend.test;

import dh.backend.model.Empleado;
import dh.backend.model.EmpleadoContratado;
import dh.backend.model.EmpleadoEfectivo;
import dh.backend.service.Liquidador;
import dh.backend.service.LiquidadorContratado;
import dh.backend.service.LiquidadorEfectivo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiquidadorTest {
    @Test
    @DisplayName("Debería emitir documento en papel cuando es efectivo")
    void TestEmpleadoEfectivo(){
        //dado
        Empleado empleado1 = new EmpleadoEfectivo("Martín", "Martínez", "4564646", 400, 40, 60);
        Liquidador liquidador = new LiquidadorEfectivo();

        //cuando
        String respuesta = liquidador.LiquidarSueldos(empleado1);

        //entonces
        assertEquals("La liquidación generada es un documento impreso Saldo a liquidar: 420.0", respuesta);
    }

    @Test
    @DisplayName("Debería emitir documento digital cuando es un empleado contratado")
    void TestEmpleadoContratado(){
        //dado
        Empleado empleado1 = new EmpleadoContratado("Pompilia", "Pompini", "7864646", 120, 7);
        Liquidador liquidador = new LiquidadorContratado();

        //cuando
        String respuesta = liquidador.LiquidarSueldos(empleado1);

        //entonces
        assertEquals("La liquidación generada es un documento digital Saldo a liquidar: 840.0", respuesta);
    }

    @Test
    @DisplayName("El liquidador debe arrojar mensaje de error cuando no es posible calcular liquidación")
    void TestEmpleadoIncorrecto(){
        //dado
        Empleado empleado1 = new EmpleadoContratado("Pompilia", "Pompini", "7864646", 120, 7);
        Liquidador liquidador = new LiquidadorEfectivo();

        //cuando
        String respuesta = liquidador.LiquidarSueldos(empleado1);

        //entonces
        assertEquals("La liquidación no pudo ser calculada", respuesta);
    }



}