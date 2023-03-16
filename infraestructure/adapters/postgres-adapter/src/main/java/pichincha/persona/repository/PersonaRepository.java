package pichincha.persona.repository;

import pichincha.persona.data.PersonaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaData, Integer> {

    List<PersonaData> findByIdentificacion(String identificacion);
}
