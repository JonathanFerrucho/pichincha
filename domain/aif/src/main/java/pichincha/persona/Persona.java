package pichincha.persona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    private Integer idPersona;
    private String nombre;
    private String genero;
    private Date fechaNacimiento;
    private String identificacion;
    private String direccion;
    private String telefono;
}
