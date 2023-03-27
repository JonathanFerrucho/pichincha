package pichincha.movimiento.data;

import pichincha.cuenta.Cuenta;
import pichincha.cuenta.data.CuentaData;
import pichincha.cuenta.data.CuentaMapper;
import pichincha.movimiento.Movimiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoviminetoMapper {

    private MoviminetoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MovimientoData toMovimientoData(Movimiento movimiento) {
        if(movimiento == null) {
            return null;
        }

        return MovimientoData.builder()
                .idMovimiento(movimiento.getIdMovimiento())
                .cuenta(movimiento.getCuenta() == null || movimiento.getCuenta().getIdCuenta() == null ?
                        CuentaData.builder().build() : CuentaMapper.toCuentaData(movimiento.getCuenta()))
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .valor(movimiento.getValor())
                .saldo(movimiento.getSaldo())
                .fechaMovimiento(movimiento.getFechaMovimiento())
                .build();
    }

    public static List<Movimiento> toMovimiento(List<MovimientoData> movimientosDta) {
        if(movimientosDta == null) {
            return Collections.emptyList();
        }

        List<Movimiento> movimientos = new ArrayList<>();

        for (MovimientoData movimientoData : movimientosDta){
            movimientos.add(toMovimiento(movimientoData));
        }

        return movimientos;
    }

    public static Movimiento toMovimiento(MovimientoData movimientoData) {
        if(movimientoData == null) {
            return null;
        }

        return Movimiento.builder()
                .idMovimiento(movimientoData.getIdMovimiento())
                .cuenta(movimientoData.getCuenta() == null || movimientoData.getCuenta().getIdCuenta() == null ?
                        Cuenta.builder().build() : CuentaMapper.toCuenta(movimientoData.getCuenta()))
                .tipoMovimiento(movimientoData.getTipoMovimiento())
                .valor(movimientoData.getValor())
                .saldo(movimientoData.getSaldo())
                .fechaMovimiento(movimientoData.getFechaMovimiento())
                .build();
    }

}
