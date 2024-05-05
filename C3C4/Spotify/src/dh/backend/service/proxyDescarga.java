package dh.backend.service;

import dh.backend.model.Usuario;
import dh.backend.model.tipoDeUsuario;

public class proxyDescarga implements servicioDescargaSpotify{

    Descarga descarga;

    public proxyDescarga(){
        descarga = new Descarga();
    }
    @Override
    public String descargar(Usuario usuario) {
        if(usuario.getTipoDeUsuario().equals(tipoDeUsuario.premium)){
            return descarga.descargar(usuario);
        } return "No se puede descargar";
    }
}
