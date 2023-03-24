package pichincha.movimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cuenta.Cuenta;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    private Integer idMovimiento;
    @NotNull(message = "la cuenta es Obligatorio")
    private Cuenta cuenta;
    @NotNull(message = "el tipo de movimiento es Obligatorio")
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Date fechaMovimiento;
}
