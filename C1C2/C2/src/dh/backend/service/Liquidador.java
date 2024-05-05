package dh.backend.service;

import dh.backend.model.Empleado;

public abstract class Liquidador {

    public String LiquidarSueldos (Empleado empleado){
        String respuesta = "La liquidación no pudo ser calculada";
            //paso 1: Calcular el sueldo
        double sueldo = calcularSueldo(empleado);

        if(sueldo >0) {
            //paso 2: Imprimir
            String imprimir = imprimir(empleado);
            //paso 3: Depositar
            depositar(empleado);

            respuesta = "La liquidación generada es un " + imprimir + " Saldo a liquidar: " + sueldo;
        }
        return respuesta;


    }

    public abstract double calcularSueldo(Empleado empleado);
    public abstract String imprimir(Empleado empleado);
    public void depositar(Empleado empleado){
        System.out.println("Estoy en la caja de ahorro "+ empleado.getNroCuenta());
    }


}
