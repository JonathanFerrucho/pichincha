package pichincha.persona;

import pichincha.persona.gateway.PersonaClient;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PersonaUseCase {

    private final PersonaClient personaClient;

    public Persona crearPersona(Persona persona){

        if(persona.getNombre() == null || persona.getIdentificacion() == null){
            throw  new IllegalArgumentException("el nombre y la identificacion de la persona es obligatorio");
        }

        if(Boolean.TRUE.equals(existePersona(persona))) {
            throw  new IllegalArgumentException("La persona ya existe con esa identificacion");
        }
        return personaClient.crearPersona(persona);
    }

    public Persona modificarPersona(Persona persona){

        if(persona.getNombre() == null ){
            throw  new IllegalArgumentException("el nombre de la persona es obligatorio");
        }

        return persona;
    }

    public void eliminarPersona(Integer id){
        personaClient.eliminarPersona(id);
    }

    public List<Persona> getPersona(Persona persona){
        return personaClient.getPersona(persona);
    }

    public Persona buscarPersonaPorId (Integer id) {
        return personaClient.buscarPersonaPorId(id);
    }

    public Persona buscarPersonaPoridentificacion (String identificacion) {
        return personaClient.buscarPersonaPorIdentificacion(identificacion).get(0);
    }

    public Boolean existePersona (Persona persona){
        return !personaClient.buscarPersonaPorIdentificacion(persona.getIdentificacion()).isEmpty();
    }
}
