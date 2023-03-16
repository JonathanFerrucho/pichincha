package pichincha.movimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cuenta.Cuenta;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    private Integer idMovimiento;
    private Cuenta cuenta;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Date fechaMovimiento;
}
