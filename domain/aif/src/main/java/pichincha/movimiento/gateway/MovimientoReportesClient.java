package pichincha.movimiento.gateway;

import pichincha.movimiento.MovimientoReporte;

import java.util.Date;
import java.util.List;

public interface MovimientoReportesClient {

    List<MovimientoReporte> reportePorClienteYFechas(Integer idCliente, Date fechaInical, Date fechaFinal);
}
