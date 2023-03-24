package pichincha.persona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    private Integer idPersona;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String genero;
    private Date fechaNacimiento;
    @NotNull(message = "la identificacion es obligatorio")
    @Size(max = 50, message = "la identificacion debe ser maximo de 50")
    private String identificacion;
    private String direccion;
    private String telefono;
}
