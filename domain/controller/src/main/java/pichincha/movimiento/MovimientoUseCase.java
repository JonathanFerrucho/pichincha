package pichincha.movimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.Cliente;
import pichincha.cuenta.Cuenta;
import pichincha.cuenta.CuentaUseCase;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.gateway.MovimientoReportesClient;
import pichincha.transaccion.Common;
import pichincha.transaccion.RespuestaEnum;

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

    public Common<Movimiento> crearMovimiento(Movimiento movimiento){

        if(movimiento== null || movimiento.getCuenta() == null || movimiento.getCuenta().getIdCuenta() == null) {
            log.log(Level.SEVERE, "El id del la cuenta es obligatorio");
            return Common.<Movimiento>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("El id del la cuenta es obligatorio")
                    .build();
        }

        Common<Cuenta> cuentaCommon= cuentaUseCase.buscarPorId(movimiento.getCuenta().getIdCuenta());

        if(cuentaCommon == null || cuentaCommon.getData() == null || cuentaCommon.getData().getIdCuenta() == null) {
            log.log(Level.SEVERE, "La cuenta suministrada  no existe");
            return Common.<Movimiento>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("La cuenta suministrada  no existe")
                    .build();
        }
        Movimiento ultimoMovimiento = movimientoClient.obtenerUltimoMovimientoPorCuenta(movimiento);

        movimiento.setSaldo(ultimoMovimiento == null || movimiento.getIdMovimiento() == null ?
                cuentaCommon.getData().getSaldoInicial(): ultimoMovimiento.getSaldo());

        if(DEBITO.equals(movimiento.getTipoMovimiento()) && Math.abs(movimiento.getValor()) > movimiento.getSaldo()) {
            log.log(Level.SEVERE, "Saldo no disponible, Saldo:" + movimiento.getSaldo() + "valor " + movimiento.getValor());
            return Common.<Movimiento>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("Saldo no disponible")
                    .build();
        }else movimiento.setSaldo(movimiento.getSaldo() + movimiento.getValor());

        if(movimiento.getFechaMovimiento() == null) {
            movimiento.setFechaMovimiento(new Date());
        }

        return Common.<Movimiento>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data( movimientoClient.crearMovimiento(movimiento))
                .build();
    }

    public Common<Movimiento> modificarMovimiento(Movimiento movimiento){

        return Common.<Movimiento>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(movimientoClient.modificarMovimiento(movimiento))
                .build();
    }

    public void eliminarMovimiento(Integer id){
        movimientoClient.eliminarMovimiento(id);
    }

    public Common<List<Movimiento>> getMovimiento(Movimiento movimiento){
        return Common.<List<Movimiento>>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(movimientoClient.getMovimiento(movimiento))
                .build();
    }

    public Common<List<MovimientoReporte>> reportePorClienteYFechas(Integer idCliente, String fechaInical, String fechaFinal){

        try {
            return Common.<List<MovimientoReporte>>builder()
                    .exito(Boolean.TRUE)
                    .mensaje(RespuestaEnum.EXITO.message)
                    .data(movimientoReportesClient.reportePorClienteYFechas(idCliente,
                            formatter.parse(fechaInical), formatter.parse(fechaFinal)))
                    .build();
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Formato de fecha no adecuando" + fechaInical);
            return Common.<List<MovimientoReporte>>builder()
                    .exito(Boolean.TRUE)
                    .mensaje("Formato de fecha no adecuando, el formato debe de ser dd/MM/yyyy")
                    .build();
        }
    }

}
