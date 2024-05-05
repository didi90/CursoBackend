package Figuras;

public class Circulo extends Figura{

    private static final double PI = 3.14;
    public Circulo(double parametro){
        super(parametro);
    }

    @Override
    public String area() {
        if(parametro <=0) return "El valor del radio debe ser mayor que cero";
        double area = 2*PI*parametro;
        return "El área del Círculo es "+area+ " unidades";
    }
}
