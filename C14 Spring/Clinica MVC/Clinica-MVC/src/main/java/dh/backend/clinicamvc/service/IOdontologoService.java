package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.model.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> buscarTodos();
    Odontologo buscarPorId(Integer id);

}
