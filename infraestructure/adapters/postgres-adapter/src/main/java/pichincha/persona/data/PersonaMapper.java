package pichincha.persona.data;

import pichincha.persona.Persona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonaMapper {

    private PersonaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PersonaData toPersonaData(Persona persona) {
        if(persona == null) {
            return null;
        }

        return PersonaData.builder()
                .idPersona(persona.getIdPersona())
                .nombre(persona.getNombre())
                .genero(persona.getGenero())
                .fechaNacimiento(persona.getFechaNacimiento())
                .identificacion(persona.getIdentificacion())
                .direccion(persona.getDireccion())
                .telefono(persona.getTelefono())
                .build();
    }

    public static List<Persona> toPersona(List<PersonaData> personasData) {
        if(personasData == null) {
            return Collections.emptyList();
        }

        List<Persona> personas= new ArrayList<>();

        for (PersonaData personaData : personasData){
            personas.add(toPersona(personaData));
        }

        return personas;
    }

    public static Persona toPersona(PersonaData personaData) {
        if(personaData == null) {
            return null;
        }

        return Persona.builder()
                .idPersona(personaData.getIdPersona())
                .nombre(personaData.getNombre())
                .genero(personaData.getGenero())
                .fechaNacimiento(personaData.getFechaNacimiento())
                .identificacion(personaData.getIdentificacion())
                .direccion(personaData.getDireccion())
                .telefono(personaData.getTelefono())
                .build();
    }
}
