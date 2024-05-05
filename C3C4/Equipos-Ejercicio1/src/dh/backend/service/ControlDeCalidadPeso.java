package dh.backend.service;

import dh.backend.model.Articulo;

public class ControlDeCalidadPeso extends ControlDeCalidad{
       @Override
    public String controlDeLaCalidad (Articulo articulo) {
        if(articulo.getPeso()>=1200 && articulo.getPeso()<=1300){
            System.out.println("Paso el control de Calidad PESO");
            return getSiguiente().controlDeLaCalidad(articulo);
        }
        return "Rechazado en Calidad PESO";
    }
}

