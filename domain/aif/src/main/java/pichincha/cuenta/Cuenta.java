package pichincha.cuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cliente.Cliente;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    private Integer idCuenta;
    @NotNull(message = "el cliente es Obligatorio")
    private Cliente cliente;
    @Size(max = 50, message = "la identificacion debe ser maximo de 50")
    private String numeroCuenta;
    private String tipoCuenta;
    @NotNull(message = "el saldo inicial es obligatorio")
    @Min(value = 0, message = "el saldo inicial debe ser mayor a 0")
    private Double saldoInicial;
    private Boolean estado;
}
