package dh.backend.dao;

import java.util.List;

public interface IDao <T>{

    T registrar(T t);
    T buscarPorID(Integer id);
    T eliminarPorID(Integer id);
    List<T> buscarTodos();



}
