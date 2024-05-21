package dh.backend.service;

import dh.backend.dao.IDao;
import dh.backend.model.Avion;

import java.util.List;

public class AvionService {

    private IDao<Avion> avionIDao;

    public AvionService(IDao<Avion> avionIDao) {
        this.avionIDao = avionIDao;
    }

    public Avion registrarAvion(Avion avion){
        return avionIDao.registrar(avion);
    }

    public Avion buscarPorID(Integer id){
        return avionIDao.buscarPorID(id);
    }

    public Avion eliminarPorId(Integer id){
        return avionIDao.eliminarPorID(id);
    }

    public List<Avion> buscarTodos(){
        return avionIDao.buscarTodos();
    }

}
