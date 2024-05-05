import java.util.ArrayList;
import java.util.List;

public class Grupo {
    List<Persona> integrantes;

    public Grupo(){
        integrantes = new ArrayList<>(); //para que no haya error de null pointer exception
    }

    public List<Persona> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Persona> integrantes) {
        this.integrantes = integrantes;
    }

    public void agregarPersona(Persona persona){
        if(persona.tiene18anios()&& persona.nombreCincoletrasomas()&& persona.nombredeContieneSoloAZ()){
            this.integrantes.add(persona);
        }


    }

}
