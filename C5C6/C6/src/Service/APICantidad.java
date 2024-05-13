package Service;

public class APICantidad {

    public int descuento(int cantidad){

        int descuento = 0;

        if(cantidad >12){
            descuento = 20;
        }
        return  descuento
    }


}
