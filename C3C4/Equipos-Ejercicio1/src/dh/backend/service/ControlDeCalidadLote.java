package dh.backend.service;

import dh.backend.model.Articulo;

public class ControlDeCalidadLote extends ControlDeCalidad {

    @Override
    public String controlDeLaCalidad(Articulo articulo) {
        if(articulo.getLote()>=1000 && articulo.getLote()<=2000){
            System.out.println("Paso el control de Calidad LOTE");
            return getSiguiente().controlDeLaCalidad(articulo);
        }
        return "Rechazado en Calidad LOTE";
    }
}
