package pichincha.cuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cliente.Cliente;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    private Integer idCuenta;
    @NotNull(message = "el cliente es Obligatorio")
    private Cliente cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
}
