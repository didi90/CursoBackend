package dh.backend.service;

import dh.backend.model.Articulo;

public abstract class ControlDeCalidad {

    private ControlDeCalidad siguiente;
    public abstract String controlDeLaCalidad(Articulo articulo);

    public ControlDeCalidad getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ControlDeCalidad siguiente) {

        this.siguiente = siguiente;
    }
}




