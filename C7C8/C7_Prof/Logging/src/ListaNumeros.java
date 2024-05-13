import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ListaNumeros {
    private static Logger LOGGER = Logger.getLogger(ListaNumeros.class);
    List<Integer> lista = new ArrayList<>();

    public void agregarNumeros(Integer numero) {
        lista.add(numero);
        if (lista.size() > 5) {
            //loguear, primero se debe instanciar
            LOGGER.info("La lista tiene más de 5 elementos");
        }

        if (lista.size() > 10) {
            //loguear, primero se debe instanciar
            LOGGER.info("La lista tiene más de 10 elementos");
        }
    }

        public double promedio(){

            double promedio =0;
            //loguear, primero se debe instanciar
        Integer suma =0;
        for (Integer numero:lista){
            suma += numero;
        }

        try{
            if(lista.size()==0){
                throw new RuntimeException("La lista no puede tener cero elementos");
            }
            promedio = (double) suma/lista.size();
        }catch (Exception e){
            LOGGER.error(e.getMessage());

        }
        LOGGER.info(("el promedio es: +"+promedio));
        return promedio;
        }
    }




