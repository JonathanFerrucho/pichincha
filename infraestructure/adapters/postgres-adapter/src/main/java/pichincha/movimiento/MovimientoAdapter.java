package pichincha.movimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import pichincha.movimiento.data.MovimientoData;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.repository.MovimientoRepository;


import pichincha.cuenta.Cuenta;
import pichincha.cuenta.CuentaAdapter;
import pichincha.cuenta.data.CuentaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
@Component
@RequiredArgsConstructor
public class MovimientoAdapter implements MovimientoClient {

    @Autowired
    private final MovimientoRepository movimientoRepository;

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        return toMovimiento(movimientoRepository.save(toMovimientoData(movimiento)));
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento) {
        return toMovimiento(movimientoRepository.findTopByCuentaOrderByIdMovimientoDesc(CuentaAdapter.toCuentaData(movimiento.getCuenta())));
    }
    @Override
    public void eliminarMovimiento(Integer id){
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> getMovimiento(Movimiento movimiento) {
        Example<MovimientoData> example= Example.of(toMovimientoData(movimiento));
        return toMovimiento(movimientoRepository.findAll(example));
    }

    @Override
    public Movimiento obtenerUltimoMovimientoPorCuenta(Movimiento movimiento) {
        return toMovimiento(movimientoRepository.findTopByCuentaOrderByIdMovimientoDesc(CuentaAdapter.toCuentaData(movimiento.getCuenta())));
    }


    public static MovimientoData toMovimientoData(Movimiento movimiento) {
        if(movimiento == null) {
            return null;
        }

        return MovimientoData.builder()
                .idMovimiento(movimiento.getIdMovimiento())
                .cuenta(movimiento.getCuenta() == null || movimiento.getCuenta().getIdCuenta() == null ?
                        CuentaData.builder().build() : CuentaAdapter.toCuentaData(movimiento.getCuenta()))
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
                        Cuenta.builder().build() : CuentaAdapter.toCuenta(movimientoData.getCuenta()))
                .tipoMovimiento(movimientoData.getTipoMovimiento())
                .valor(movimientoData.getValor())
                .saldo(movimientoData.getSaldo())
                .fechaMovimiento(movimientoData.getFechaMovimiento())
                .build();
    }


}