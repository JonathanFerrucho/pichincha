package pichincha.cliente;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.persona.Persona;
import pichincha.persona.PersonaUseCase;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteUseCaseTest {

    Cliente cliente;
    Cliente clienteRest;
    Persona personaRest;

    private static final String TEST = "TEST";
    private static final Date TEST_DATE = new Date();
    Integer UNO=1;

    @Mock
    private ClienteClient clienteClient;
    @Mock
    private PersonaUseCase personaUseCase;

    @InjectMocks
    private ClienteUseCase clienteUseCase;

    @Before
    public void init(){

        clienteUseCase= new ClienteUseCase(clienteClient, personaUseCase);

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

    }

    @Test
    public void crearCliente() {
        when(clienteClient.crearCliene(any(Cliente.class))).thenReturn(clienteRest);
        when(personaUseCase.existePersona(any(Persona.class))).thenReturn(Boolean.TRUE);
        when(personaUseCase.buscarPersonaPoridentificacion(anyString())).thenReturn(personaRest);

        cliente = clienteUseCase.crearCliente(cliente);

        assertThat(cliente.getIdCliente()).isEqualTo(1);
        assertThat(cliente.getEstado()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void crearClientePersonaNull() {
        cliente.setPersona(null);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            clienteUseCase.crearCliente(cliente);
        });

        Assertions.assertEquals("el nombre y la identificacion de la persona es obligatorio", thrown.getMessage());
    }

    @Test
    public void crearClientePersonaNueva() {
        when(clienteClient.crearCliene(any(Cliente.class))).thenReturn(clienteRest);
        when(personaUseCase.crearPersona(any(Persona.class))).thenReturn(personaRest);

        cliente = clienteUseCase.crearCliente(cliente);

        assertThat(cliente.getIdCliente()).isEqualTo(1);
        assertThat(cliente.getEstado()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void eliminarCliente() {
        doNothing().when(clienteClient).eliminarCliente(anyInt());
        clienteUseCase.eliminarCliente(1);

    }

    @Test
    public void getCliente() {
        when(clienteClient.getCliente(any(Cliente.class))).thenReturn(List.of(clienteRest));

        List<Cliente> clientes = clienteUseCase.getCliente(cliente);

        assertThat(clientes).isNotEmpty();
    }

    @Test
    public void buscarPorId() {
        when(clienteClient.buscarPorId(anyInt())).thenReturn(clienteRest);

        cliente = clienteUseCase.buscarPorId(1);

        assertThat(cliente.getIdCliente()).isEqualTo(1);
        assertThat(cliente.getEstado()).isEqualTo(Boolean.TRUE);
    }
}
