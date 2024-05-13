package Service;

import Model.Producto;
import Model.Tarjeta;

public class FacadeDescuento {

    APICantidad apiCantidad;
    APITarjeta apiTarjeta;
    APIProducto apiProducto;

    public  FacadeDescuento(){
        apiCantidad = new APICantidad();
        apiProducto = new APIProducto();
        apiTarjeta = new APITarjeta();

    }

    public int calculoDescuento(Tarjeta tarjeta, Producto producto, int cantidad){
        int sumaDescuentos = 0;
        sumaDescuentos += apiCantidad.descuento(cantidad);
        sumaDescuentos += apiTarjeta.descuento(tarjeta);
        sumaDescuentos += apiProducto.descuento(producto);



    }




}
