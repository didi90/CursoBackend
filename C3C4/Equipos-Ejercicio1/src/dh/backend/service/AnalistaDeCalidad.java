package dh.backend.service;

import dh.backend.model.Articulo;

public class AnalistaDeCalidad {

    public String cadenaCalidad(Articulo articulo){

        ControlDeCalidad controlDeCalidad1 = new ControlDeCalidadLote();
        ControlDeCalidad controlDeCalidad2 = new ControlDeCalidadPeso();
        ControlDeCalidad controlDeCalidad3 = new ControlDeCalidadEnvase();

        controlDeCalidad1.setSiguiente(controlDeCalidad2);
        controlDeCalidad2.setSiguiente(controlDeCalidad3);

        return controlDeCalidad1.controlDeLaCalidad(articulo);
    }

}
