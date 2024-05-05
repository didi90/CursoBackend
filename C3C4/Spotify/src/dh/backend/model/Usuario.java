package dh.backend.model;

public class Usuario {
    private String identificador;

    private tipoDeUsuario tipoDeUsuario;

    public Usuario(String identificador, dh.backend.model.tipoDeUsuario tipoDeUsuario) {
        this.identificador = identificador;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public dh.backend.model.tipoDeUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(dh.backend.model.tipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
}
