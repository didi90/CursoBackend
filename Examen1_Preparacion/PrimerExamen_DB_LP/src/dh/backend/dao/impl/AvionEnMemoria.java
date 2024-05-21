package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.model.Avion;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvionEnMemoria implements IDao<Avion> {
    private Logger LOGGER = Logger.getLogger(AvionEnMemoria.class);
    List<Avion> aviones = new ArrayList<>();

    @Override
    public Avion registrar(Avion avion) {
        Integer id = aviones.size()+1;
        avion.setId(id);

        aviones.add(avion);
        LOGGER.info("Avion guardado: "+ avion);
        return avion;
    }

    @Override
    public Avion buscarPorID(Integer id) {
        for(Avion avion: aviones){
            if(avion.getId().equals(id)){
                LOGGER.info("Avión encontrado: "+ avion);
                return avion;
            }
        }
        LOGGER.info("Avión no encontrado");
        return null;
    }

    @Override
    public Avion eliminarPorID(Integer id) {
        Iterator<Avion> iterator = aviones.iterator();
        while (iterator.hasNext()) {
            Avion avion = iterator.next();
            if (avion.getId().equals(id)) {
                iterator.remove(); // Eliminar el avión de la lista
                LOGGER.info("Avión encontrado y eliminado: " + avion);
                return avion;
            }
        }
        LOGGER.info("Avión no encontrado");
        return null;
    }



    @Override
    public List<Avion> buscarTodos() {
        return aviones;
    }
}
