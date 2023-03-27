package pichincha.persona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    private Integer idPersona;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Pattern(regexp = "^(Femenino|Masculino)$", message = "Solo puede tomar valores de: Femenino o Masculino")
    private String genero;
    @Past(message = "La fecha de nacimiento debe ser inferior a la de hoy")
    private Date fechaNacimiento;
    @NotNull(message = "la identificacion es obligatorio")
    @Size(max = 50, message = "la identificacion debe ser maximo de 50")
    private String identificacion;
    private String direccion;
    private String telefono;
}
