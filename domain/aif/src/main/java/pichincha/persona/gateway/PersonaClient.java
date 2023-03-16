package pichincha.persona.gateway;

import pichincha.persona.Persona;

import java.util.List;

public interface PersonaClient {

     Persona crearPersona(Persona persona);
     Persona modificarPersona(Persona persona);
     void eliminarPersona(Integer id);
     List<Persona> getPersona(Persona persona);
     Persona buscarPersonaPorId(Integer id);
     List<Persona> buscarPersonaPorIdentificacion(String identificacion);

}
