package Figuras;//https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=668:clases-y-metodos-abstractos-en-java-abstract-class-clases-del-api-ejemplos-codigo-y-ejercicios-cu00695b&catid=68&Itemid=188#:~:text=La%20declaraci%C3%B3n%20de%20que%20una,crear%20objetos%20de%20ese%20tipo.

public abstract class Figura {
    public double parametro;

    //unificar los parámetros de las otras clases
    //aquí no creo objetos
    public Figura(double parametro) {
        this.parametro = parametro;
    }

    //que todas las subclases sobreescriban el método declarado como abstracto
    public abstract String area();


}
