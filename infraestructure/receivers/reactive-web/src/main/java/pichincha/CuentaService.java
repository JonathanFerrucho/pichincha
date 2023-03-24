package pichincha;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pichincha.cuenta.Cuenta;
import pichincha.cuenta.CuentaUseCase;

import javax.validation.Valid;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cuenta", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentaService {

    private final CuentaUseCase cuentaUseCase;


    @PostMapping(path = "crearCuenta")
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody(required = true) @Valid Cuenta cuenta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cuentaUseCase.crearCuenta(cuenta));
    }

    @PutMapping(path = "modificarCuenta")
    public ResponseEntity<Cuenta> modificarCuenta(@RequestBody(required = true) @Valid Cuenta cuenta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cuentaUseCase.crearCuenta(cuenta));
    }

    @DeleteMapping(path = "eliminar/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id) {
        cuentaUseCase.eliminarCuenta(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Cuenta eliminada Satisfactoriamente");
    }

    @GetMapping(path = "buscar/{id}")
    public ResponseEntity<Cuenta> buscarCuenta(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body( cuentaUseCase.buscarPorId(id));
    }
}
