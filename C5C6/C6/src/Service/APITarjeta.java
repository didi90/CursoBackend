package Service;

import Model.Tarjeta;

public class APITarjeta {

    public int descuento(Tarjeta tarjeta){

        int descuento = 0;

        if(tarjeta.getBanco.equals("Star Bank")){
            descuento = 20;
        }
        return  descuento;
    }


}
