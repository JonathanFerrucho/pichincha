package pichincha.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.persona.Persona;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Integer idCliente;
    @NotNull(message = "la Persona es Obligatoria")
    private Persona persona;
    private String clave;
    private Boolean estado;
}
