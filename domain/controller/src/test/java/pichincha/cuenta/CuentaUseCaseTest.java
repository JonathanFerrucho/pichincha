package pichincha.cuenta;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pichincha.cliente.Cliente;
import pichincha.cliente.ClienteUseCase;
import pichincha.cuenta.gateway.CuentaClient;
import pichincha.persona.Persona;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CuentaUseCaseTest {

    Cliente cliente;
    Cliente clienteRest;
    Persona personaRest;
    Cuenta cuenta;
    Cuenta cuentaRest;

    private static final String TEST = "TEST";
    private static final Date TEST_DATE = new Date();

    @Mock
    private CuentaClient cuentaClient;
    @Mock
    private ClienteUseCase clienteUseCase;

    @InjectMocks
    private CuentaUseCase cuentaUseCase;

    public CuentaUseCaseTest() {
    }

    @Before
    public void init(){

        cuentaUseCase= new CuentaUseCase(cuentaClient, clienteUseCase);

        personaRest= Persona.builder()
                        .idPersona(1)
                        .identificacion(TEST)
                        .build();

        cliente = Cliente.builder()
                .persona(Persona.builder()
                        .identificacion(TEST)
                        .build())
                .clave(TEST)
                .build();

        clienteRest= Cliente.builder()
                .idCliente(1)
                .persona(Persona.builder().build())
                .estado(Boolean.TRUE)
                .clave(TEST)
                .build();

        cuenta = Cuenta.builder()
                    .cliente(clienteRest)
                    .build();
        cuentaRest = Cuenta.builder()
                .idCuenta(1)
                .cliente(clienteRest)
                .estado(Boolean.TRUE)
                .build();

    }

    @Test
    public void crearCuenta() {
        when(clienteUseCase.buscarPorId(anyInt())).thenReturn(clienteRest);
        when(cuentaClient.crearCuenta(any(Cuenta.class))).thenReturn(cuentaRest);


        cuenta = cuentaUseCase.crearCuenta(cuenta);

        assertThat(cuenta.getIdCuenta()).isEqualTo(1);
        assertThat(cuenta.getEstado()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void crearCuentaClienteNull() {
        cuenta.setCliente(null);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cuentaUseCase.crearCuenta(cuenta);
        });

        Assertions.assertEquals("El id del cliente es obligatorio", thrown.getMessage());
    }

    @Test
    public void crearCuentaClienteNoExiste() {
        when(clienteUseCase.buscarPorId(anyInt())).thenReturn(null);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cuentaUseCase.crearCuenta(cuenta);
        });

        Assertions.assertEquals("El  cliente suministrado  no existe", thrown.getMessage());
    }

    @Test
    public void eliminarCuenta() {
        doNothing().when(cuentaClient).eliminarCuenta(anyInt());
        cuentaUseCase.eliminarCuenta(1);

    }

    @Test
    public void getCuenta() {
        when(cuentaClient.getCuenta(any(Cuenta.class))).thenReturn(List.of(cuentaRest));

        List<Cuenta> clientes = cuentaUseCase.getCuenta(cuenta);

        assertThat(clientes).isNotEmpty();
    }

    @Test
    public void buscarPorId() {
        when(cuentaClient.buscarPorId(anyInt())).thenReturn(cuentaRest);

        cuenta = cuentaUseCase.buscarPorId(1);

        assertThat(cuenta.getIdCuenta()).isEqualTo(1);
        assertThat(cuenta.getEstado()).isEqualTo(Boolean.TRUE);
    }
}
