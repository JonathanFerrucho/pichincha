package pichincha.movimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pichincha.cuenta.data.CuentaData;
import pichincha.movimiento.data.MovimientoData;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoData, Integer> {


    MovimientoData findTopByCuentaOrderByIdMovimientoDesc(CuentaData cuenta);

    @Query(value = "select m.fecha_movimiento, p.nombre, c.numero_cuenta, c.tipo_cuenta, " +
            " c.saldo_inicial, c.estado, m.valor, m.saldo " +
            " from Movimiento m " +
            " left join cuenta c on c.id_cuenta = m.id_cuenta " +
            " left join cliente cl  on cl.id_cliente = c.id_cliente " +
            " left join persona p on p.id_persona = cl.id_persona " +
            " where c.id_cliente = :idCliente and date(m.fecha_movimiento) between date(:fechaInical) and date(:fechaFinal) " +
            " order by m.fecha_movimiento ",
            nativeQuery = true)
    List<Object[]> reportePorClienteYFechas(
            @Param("idCliente") Integer idCliente, @Param("fechaInical") Date fechaInical, @Param("fechaFinal") Date fechaFinal);
}
