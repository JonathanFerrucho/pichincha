package pichincha.persona;

import pichincha.persona.gateway.PersonaClient;
import lombok.RequiredArgsConstructor;
import pichincha.transaccion.Common;
import pichincha.transaccion.RespuestaEnum;

import java.util.List;

@RequiredArgsConstructor
public class PersonaUseCase {

    private final PersonaClient personaClient;

    public Common<Persona> crearPersona(Persona persona){

        if(persona== null){
            return Common.<Persona>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("la persona es obligatoria")
                    .build();
        }

        if(Boolean.TRUE.equals(existePersona(persona))) {
            return Common.<Persona>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("La persona ya existe con esa identificacion")
                    .build();
        }
        return Common.<Persona>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(personaClient.crearPersona(persona))
                .build();
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
