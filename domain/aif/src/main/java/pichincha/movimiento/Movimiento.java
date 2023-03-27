package pichincha.movimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cuenta.Cuenta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(Debito|Credito)$", message = "Solo puede tomar valores de: Debito o Credito")
    private String tipoMovimiento;
    @NotNull(message = "el valor del movimiento es obligatorio")
    private Double valor;
    private Double saldo;
    private Date fechaMovimiento;
}
