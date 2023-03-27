package pichincha;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pichincha.movimiento.Movimiento;
import pichincha.movimiento.MovimientoReporte;
import pichincha.movimiento.MovimientoUseCase;
import pichincha.transaccion.Common;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "movimiento", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovimientoService {

    private final MovimientoUseCase movimientoUseCase;


    @PostMapping()
    public ResponseEntity<Common<Movimiento>> crearMovimiento(@RequestBody(required = true) @Valid Movimiento movimiento) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movimientoUseCase.crearMovimiento(movimiento));
    }

    @PutMapping()
    public ResponseEntity<Common<Movimiento>> modificarMovimiento(@RequestBody(required = true) @Valid Movimiento movimiento) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movimientoUseCase.modificarMovimiento(movimiento));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> eliminarMovimiento(@PathVariable Integer id) {
        movimientoUseCase.eliminarMovimiento(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Movimiento eliminada Satisfactoriamente");
    }

    @GetMapping(path = "reporte")
    public ResponseEntity<Common<List<MovimientoReporte>>> reportePorClienteYFechas(@RequestParam Integer idCliente, @RequestParam String fechaInical, @RequestParam String fechaFinal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movimientoUseCase.reportePorClienteYFechas(idCliente, fechaInical, fechaFinal));
    }
}
