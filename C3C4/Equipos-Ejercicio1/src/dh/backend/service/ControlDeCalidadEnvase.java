package dh.backend.service;

import dh.backend.model.Articulo;

public class ControlDeCalidadEnvase extends ControlDeCalidad{
    @Override
    public String controlDeLaCalidad (Articulo articulo) {
        if(articulo.getEnvasado().equalsIgnoreCase("sano") || articulo.getEnvasado().equalsIgnoreCase("casi sano") ){
            System.out.println("Paso el control de Calidad ENVASE");
            return "Cumple con todos los requisitos. Fue ACEPTADO";
        } return "No cumple con ninguno de los requisitos";

    }
}
