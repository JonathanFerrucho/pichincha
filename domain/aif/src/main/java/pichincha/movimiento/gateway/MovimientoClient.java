package pichincha.movimiento.gateway;

import pichincha.movimiento.Movimiento;

import java.util.List;

public interface MovimientoClient {

     Movimiento crearMovimiento(Movimiento movimiento);
     Movimiento modificarMovimiento(Movimiento movimiento);
     void eliminarMovimiento(Integer id);
     List<Movimiento> getMovimiento(Movimiento movimiento);
     Movimiento obtenerUltimoMovimientoPorCuenta(Movimiento movimiento);

}
