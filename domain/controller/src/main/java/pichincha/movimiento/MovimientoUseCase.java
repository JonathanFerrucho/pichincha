package pichincha.movimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cuenta.Cuenta;
import pichincha.cuenta.CuentaUseCase;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.gateway.MovimientoReportesClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class MovimientoUseCase {

    private final MovimientoClient movimientoClient;
    private final MovimientoReportesClient movimientoReportesClient;
    private final CuentaUseCase cuentaUseCase;

    private  static final  String DEBITO = "Debito";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Movimiento crearMovimiento(Movimiento movimiento){

        if(movimiento== null || movimiento.getCuenta() == null || movimiento.getCuenta().getIdCuenta() == null) {
            log.log(Level.SEVERE, "El id del la cuenta es obligatorio");
            throw  new IllegalArgumentException("El id del la cuenta es obligatorio");
        }

        Cuenta cuenta= cuentaUseCase.buscarPorId(movimiento.getCuenta().getIdCuenta());

        if(cuenta == null || cuenta.getIdCuenta() == null) {
            log.log(Level.SEVERE, "La cuenta suministrada  no existe");
            throw  new IllegalArgumentException("La cuenta suministrada  no existe");
        }
        Movimiento ultimoMovimiento = movimientoClient.obtenerUltimoMovimientoPorCuenta(movimiento);

        movimiento.setSaldo(ultimoMovimiento == null || movimiento.getIdMovimiento() == null ?
                cuenta.getSaldoInicial(): ultimoMovimiento.getSaldo());

        if(DEBITO.equals(movimiento.getTipoMovimiento()) && Math.abs(movimiento.getValor()) > movimiento.getSaldo()) {
            log.log(Level.SEVERE, "Saldo no disponible, Saldo:" + movimiento.getSaldo() + "valor " + movimiento.getValor());
            throw  new IllegalArgumentException("Saldo no disponible");
        }else movimiento.setSaldo(movimiento.getSaldo() + movimiento.getValor());

        if(movimiento.getFechaMovimiento() == null) {
            movimiento.setFechaMovimiento(new Date());
        }
        return movimientoClient.crearMovimiento(movimiento);
    }

    public Movimiento   modificarMovimiento(Movimiento movimiento){

        return movimientoClient.modificarMovimiento(movimiento);
    }

    public void eliminarMovimiento(Integer id){
        movimientoClient.eliminarMovimiento(id);
    }

    public List<Movimiento> getMovimiento(Movimiento movimiento){
        return movimientoClient.getMovimiento(movimiento);
    }

    public List<MovimientoReporte> reportePorClienteYFechas(Integer idCliente, String fechaInical, String fechaFinal){

        try {
            return movimientoReportesClient.reportePorClienteYFechas(idCliente, formatter.parse(fechaInical), formatter.parse(fechaFinal));
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Formato de fecha no adecuando" + fechaInical);
            throw  new IllegalArgumentException("Formato de fecha no adecuando, el formato debe de ser dd/MM/yyyy");
        }
    }

}
