package dh.backend.model;

public class Articulo {

    private String nombre;
    private int lote;
    private int peso;
    private String envasado;

    public Articulo(String envasado, int peso, int lote, String nombre) {
        this.envasado = envasado;
        this.peso = peso;
        this.lote = lote;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEnvasado() {
        return envasado;
    }

    public int getPeso() {
        return peso;
    }

    public int getLote() {
        return lote;
    }
}
