package pichincha.cliente.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.persona.data.PersonaData;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_persona", nullable=false)
    private PersonaData persona;
    private String clave;
    private Boolean estado;
}

