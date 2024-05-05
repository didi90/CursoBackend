package dh.backend.service;

import dh.backend.model.Empleado;
import dh.backend.model.EmpleadoContratado;

public class LiquidadorContratado extends Liquidador{


    @Override
    public double calcularSueldo(Empleado empleado) {
        if(empleado instanceof EmpleadoContratado empleadoContratado){
            //el instaceof me "castea" lo que necesito instanciar esto para que me salga lo específico del contratado
            //EmpleadoContratado empleadoContratado = ((EmpleadoContratado) empleado);
            return empleadoContratado.getTarifaPorHora()*empleadoContratado.getCantHoras();
        }
        return 0;

    }

    @Override
    public String imprimir(Empleado empleado) {
        return "documento digital";
    }
}
