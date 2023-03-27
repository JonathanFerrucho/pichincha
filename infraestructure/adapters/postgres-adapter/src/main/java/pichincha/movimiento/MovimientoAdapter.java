package pichincha.movimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import pichincha.cuenta.data.CuentaMapper;
import pichincha.movimiento.data.MovimientoData;
import pichincha.movimiento.data.MoviminetoMapper;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.repository.MovimientoRepository;

import java.util.List;

@Log
@Component
@RequiredArgsConstructor
public class MovimientoAdapter implements MovimientoClient {

    @Autowired
    private final MovimientoRepository movimientoRepository;

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        return MoviminetoMapper.toMovimiento(movimientoRepository.save(MoviminetoMapper.toMovimientoData(movimiento)));
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento) {
        return MoviminetoMapper.toMovimiento(movimientoRepository.findTopByCuentaOrderByIdMovimientoDesc(CuentaMapper.toCuentaData(movimiento.getCuenta())));
    }
    @Override
    public void eliminarMovimiento(Integer id){
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> getMovimiento(Movimiento movimiento) {
        Example<MovimientoData> example= Example.of(MoviminetoMapper.toMovimientoData(movimiento));
        return MoviminetoMapper.toMovimiento(movimientoRepository.findAll(example));
    }

    @Override
    public Movimiento obtenerUltimoMovimientoPorCuenta(Movimiento movimiento) {
        return MoviminetoMapper.toMovimiento(movimientoRepository.findTopByCuentaOrderByIdMovimientoDesc(CuentaMapper.toCuentaData(movimiento.getCuenta())));
    }
}