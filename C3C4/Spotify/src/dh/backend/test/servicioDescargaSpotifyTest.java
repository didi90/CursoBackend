package dh.backend.test;

import dh.backend.model.Usuario;
import dh.backend.model.tipoDeUsuario;
import dh.backend.service.proxyDescarga;
import dh.backend.service.servicioDescargaSpotify;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class servicioDescargaSpotifyTest {

    @Test
    @DisplayName("Descarga la canción")

    void test1(){
        Usuario usuario1 = new Usuario("1234", tipoDeUsuario.premium);

        servicioDescargaSpotify servicioDescargaSpotify = new proxyDescarga();

        String respuesta = servicioDescargaSpotify.descargar(usuario1);

        assertEquals("Ya se esta descargando la canción", respuesta);
    }
    @Test
    @DisplayName("NO Descarga la canción")
    void test12(){
        Usuario usuario2 = new Usuario("1234", tipoDeUsuario.free);

        servicioDescargaSpotify servicioDescargaSpotify = new proxyDescarga();

        String respuesta2 = servicioDescargaSpotify.descargar(usuario2);

        assertEquals("No se puede descargar", respuesta2);
    }
}