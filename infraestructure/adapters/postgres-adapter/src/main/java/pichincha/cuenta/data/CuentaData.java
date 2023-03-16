package pichincha.cuenta.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pichincha.cliente.data.ClienteData;

import javax.persistence.*;

@Entity
@Table(name = "Cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cliente", nullable=false)
    private ClienteData cliente;
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    private Boolean estado;
}