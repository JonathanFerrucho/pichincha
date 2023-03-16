package pichincha.persona;

import pichincha.persona.data.PersonaData;
import pichincha.persona.gateway.PersonaClient;
import pichincha.persona.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaClient {

    @Autowired
    private final PersonaRepository personaRepository;

    @Override
    public Persona crearPersona(Persona persona) {
        return toPersona(personaRepository.save(toPersonaData(persona)));
    }

    @Override
    public Persona modificarPersona(Persona persona) {
        return null;
    }

    @Override
    public void eliminarPersona(Integer id){
        personaRepository.deleteById(id);
    }

    @Override
    public List<Persona> getPersona(Persona persona) {
        Example<PersonaData> example= Example.of(toPersonaData(persona));
        return toPersona(personaRepository.findAll(example));
    }

    @Override
    public Persona buscarPersonaPorId(Integer id) {
        Optional<PersonaData> optPersona = personaRepository.findById(id);
        return toPersona(optPersona.isPresent() ? optPersona.get() : null);
    }

    @Override
    public List<Persona> buscarPersonaPorIdentificacion(String identificacion) {
        return toPersona(personaRepository.findByIdentificacion(identificacion));
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