package pichincha;

import org.springframework.http.MediaType;
import pichincha.cliente.Cliente;
import pichincha.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pichincha.transaccion.Common;

import javax.validation.Valid;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteService {

    private final ClienteUseCase clienteUseCase;


    @PostMapping()
    public ResponseEntity<Common<Cliente>> crearCliente(@RequestBody(required = true) @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteUseCase.crearCliente(cliente));
    }


    @PutMapping()
    public ResponseEntity<Common<Cliente>> modificarCliente(@RequestBody(required = true) @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteUseCase.crearCliente(cliente));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id) {
        clienteUseCase.eliminarCliente(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Cliente eliminada Satisfactoriamente");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Common<Cliente>> buscarCliente(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body( clienteUseCase.buscarPorId(id));
    }
}
