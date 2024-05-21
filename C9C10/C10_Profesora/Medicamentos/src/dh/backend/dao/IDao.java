package dh.backend.dao;

import java.util.List;

// la T es un dato genérico
public interface IDao <T>{

    T registrar(T t);
    T buscarPorCampo(String campo);
    T buscarPorID(Integer id);
    List<T> buscarTodos();



}
