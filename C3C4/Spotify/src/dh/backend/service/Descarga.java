package dh.backend.service;

import dh.backend.model.Usuario;

public class Descarga implements servicioDescargaSpotify{
    @Override
    public String descargar(Usuario usuario) {
        return "Ya se esta descargando la canción";
    }
}
