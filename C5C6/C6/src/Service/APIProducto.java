package Service;

import Model.Producto;

public class APIProducto {

    public int descuento(Producto producto){

        int descuento = 0;

        if(producto.getTipo.equals("Latas")){
            descuento = 20;
        }
        return  descuento;
    }

}
