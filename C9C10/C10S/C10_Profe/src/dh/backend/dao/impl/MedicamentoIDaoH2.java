package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.model.Medicamento;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class MedicamentoIDaoH2 implements IDao<Medicamento> {
    private  static Logger LOGGER = Logger.getLogger(MedicamentoIDaoH2.class);
    @Override

    public Medicamento registrar(Medicamento medicamento) {
        Connection connection = null;




        return null;
    }

    @Override
    public Medicamento buscarPorCampo(String campo) {
        return null;
    }

    //Configurando la persistencia

}
