package pichincha.persona;

import pichincha.persona.data.PersonaData;
import pichincha.persona.data.PersonaMapper;
import pichincha.persona.gateway.PersonaClient;
import pichincha.persona.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaClient {

    @Autowired
    private final PersonaRepository personaRepository;

    @Override
    public Persona crearPersona(@Valid Persona persona) {
        return PersonaMapper.toPersona(personaRepository.save( PersonaMapper.toPersonaData(persona)));
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
        Example<PersonaData> example= Example.of( PersonaMapper.toPersonaData(persona));
        return PersonaMapper.toPersona(personaRepository.findAll(example));
    }

    @Override
    public Persona buscarPersonaPorId(Integer id) {
        Optional<PersonaData> optPersona = personaRepository.findById(id);
        return PersonaMapper.toPersona(optPersona.isPresent() ? optPersona.get() : null);
    }

    @Override
    public List<Persona> buscarPersonaPorIdentificacion(String identificacion) {
        return PersonaMapper.toPersona(personaRepository.findByIdentificacion(identificacion));
    }
}