package pichincha.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.persona.Persona;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Integer idCliente;
    private Persona persona;
    private String clave;
    private Boolean estado;
}
