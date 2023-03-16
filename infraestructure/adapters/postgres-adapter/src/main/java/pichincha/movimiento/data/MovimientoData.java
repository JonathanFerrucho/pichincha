package pichincha.movimiento.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cuenta.data.CuentaData;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Movimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cuenta", nullable=false)
    private CuentaData cuenta;
    @Column(name="tipo_movimiento")
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    @Column(name="fecha_movimiento")
    private Date fechaMovimiento;
}