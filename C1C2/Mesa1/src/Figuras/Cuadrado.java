package Figuras;

public class Cuadrado extends Figura {

    public Cuadrado(double parametro){
        super(parametro);
    }

    @Override
    public String area() {
        if(parametro <=0) return "El valor del lado debe ser mayor que cero";
        double area = parametro*parametro;
        return "El área del cuadrado es "+area+ " unidades";
    }
}
