package pichincha.persona.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;
    private String nombre;
    private String genero;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private String identificacion;
    private String direccion;
    private String telefono;
}