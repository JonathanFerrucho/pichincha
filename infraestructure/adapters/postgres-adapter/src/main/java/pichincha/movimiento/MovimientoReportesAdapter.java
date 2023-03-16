package pichincha.movimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pichincha.movimiento.gateway.MovimientoReportesClient;
import pichincha.movimiento.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Log
@Component
@RequiredArgsConstructor
public class MovimientoReportesAdapter implements MovimientoReportesClient {

    @Autowired
    private final MovimientoRepository movimientoRepository;


    @Override
    public List<MovimientoReporte> reportePorClienteYFechas(Integer idCliente, Date fechaInical, Date fechaFinal) {
        return toMovimientoReporte(movimientoRepository.reportePorClienteYFechas(idCliente, fechaInical, fechaFinal));
    }

    public static List<MovimientoReporte> toMovimientoReporte(List<Object[]> objects) {
        if(objects == null) {
            return Collections.emptyList();
        }

        List<MovimientoReporte> movimientos = new ArrayList<>();

        for (Object[] object : objects){
            movimientos.add(toMovimientoReporte(object));
        }

        return movimientos;
    }

    public static MovimientoReporte toMovimientoReporte(Object[] object) {
        if(object == null) {
            return null;
        }

        return MovimientoReporte.builder()
                .fecha( (Date) object[0])
                .cliente(String.valueOf(object[1]))
                .numeroCuenta(String.valueOf(object[2]) )
                .tipo(String.valueOf(object[3]))
                .saldoInicial(Double.parseDouble(object[4].toString()))
                .estado(Boolean.valueOf(object[5].toString()))
                .movimiento(Double.parseDouble(object[6].toString()))
                .saldoDisponible(Double.parseDouble(object[7].toString()))
                .build();
    }

}
