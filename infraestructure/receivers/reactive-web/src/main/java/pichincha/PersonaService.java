package pichincha;

import org.springframework.http.MediaType;
import pichincha.persona.Persona;
import pichincha.persona.PersonaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pichincha.transaccion.Common;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "persona", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonaService {

    private final PersonaUseCase personaUseCase;


    @PostMapping()
    public ResponseEntity<Common<Persona>> crearPersona(@RequestBody(required = true) @Valid Persona persona) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personaUseCase.crearPersona(persona));
    }

    @PutMapping()
    public ResponseEntity<Persona> modificarPersona(@RequestBody(required = true) @Valid Persona persona) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personaUseCase.modificarPersona(persona));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Integer id) {
        personaUseCase.eliminarPersona(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Persona eliminada Satisfactoriamente");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Persona> buscarPersona(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body( personaUseCase.buscarPersonaPorId(id));
    }

    @PostMapping(path = "getPersona")
    public ResponseEntity<List<Persona>> getPersona(@RequestBody(required = true) Persona persona) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personaUseCase.getPersona(persona));
    }
}
