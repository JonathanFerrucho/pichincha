package pichincha.movimiento;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pichincha.cliente.Cliente;
import pichincha.cuenta.Cuenta;
import pichincha.cuenta.CuentaUseCase;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.gateway.MovimientoReportesClient;
import pichincha.persona.Persona;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovimientoUseCaseTest {

    @Mock
    private MovimientoClient movimientoClient;
    @Mock
    private MovimientoReportesClient movimientoReportesClient;
    @Mock
    private CuentaUseCase cuentaUseCase;

    @InjectMocks
    private MovimientoUseCase movimientoUseCase;

    Cliente clienteRest;
    Persona personaRest;
    Cuenta cuentaRest;
    Movimiento movimiento;
    Movimiento movimientoRest;
    MovimientoReporte movimientoReporte;

    private static final String TEST = "TEST";

    public MovimientoUseCaseTest() {
    }

    @Before
    public void init(){

        movimientoUseCase= new MovimientoUseCase(movimientoClient, movimientoReportesClient, cuentaUseCase);

        personaRest= Persona.builder()
                        .idPersona(1)
                        .identificacion(TEST)
                        .build();

        clienteRest= Cliente.builder()
                .idCliente(1)
                .persona(Persona.builder().build())
                .estado(Boolean.TRUE)
                .clave(TEST)
                .build();

        cuentaRest = Cuenta.builder()
                .idCuenta(1)
                .cliente(clienteRest)
                .estado(Boolean.TRUE)
                .saldoInicial(100D)
                .build();

        movimiento= Movimiento.builder()
                .cuenta(cuentaRest)
                .saldo(100D)
                .tipoMovimiento("Debito")
                .valor(-50D)
                .build();

        movimientoRest = Movimiento.builder()
                .idMovimiento(1)
                .cuenta(cuentaRest)
                .saldo(100D)
                .build();

        movimientoReporte = movimientoReporte.builder()
                .fecha(new Date())
                .cliente(TEST)
                .build();
    }

    @Test
    public void crearMovimiento() {
        when(cuentaUseCase.buscarPorId(anyInt())).thenReturn(cuentaRest);
        when(movimientoClient.obtenerUltimoMovimientoPorCuenta(any(Movimiento.class)))
                .thenReturn(movimiento);
        when(movimientoClient.crearMovimiento(any(Movimiento.class)))
                .thenReturn(movimientoRest);


        movimiento = movimientoUseCase.crearMovimiento(movimiento);

        assertThat(movimiento.getIdMovimiento()).isEqualTo(1);
    }

    @Test
    public void crearMovimientoCuentaNull() {
        movimiento.setCuenta(null);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movimientoUseCase.crearMovimiento(movimiento);
        });

        Assertions.assertEquals("El id del la cuenta es obligatorio", thrown.getMessage());
    }

    @Test
    public void crearMovimientoCuentaNoExiste() {
        when(cuentaUseCase.buscarPorId(anyInt())).thenReturn(null);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movimientoUseCase.crearMovimiento(movimiento);
        });

        Assertions.assertEquals("La cuenta suministrada  no existe", thrown.getMessage());
    }

    @Test
    public void crearMovimientoNoSaldo() {
        when(cuentaUseCase.buscarPorId(anyInt())).thenReturn(cuentaRest);
        when(movimientoClient.obtenerUltimoMovimientoPorCuenta(any(Movimiento.class)))
                .thenReturn(movimientoRest);
        movimiento.setValor(-1000D);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movimientoUseCase.crearMovimiento(movimiento);
        });

        Assertions.assertEquals("Saldo no disponible", thrown.getMessage());
    }

    @Test
    public void eliminarMovimiento() {
        doNothing().when(movimientoClient).eliminarMovimiento(anyInt());
        movimientoUseCase.eliminarMovimiento(1);

    }

    @Test
    public void getMovimiento() {
        when(movimientoClient.getMovimiento(any(Movimiento.class))).thenReturn(List.of(movimientoRest));

        List<Movimiento> movimientos = movimientoUseCase.getMovimiento(movimiento);

        assertThat(movimientos).isNotEmpty();
    }

    @Test
    public void reportePorClienteYFechas() {
        when(movimientoReportesClient.reportePorClienteYFechas(anyInt(), any(Date.class), any(Date.class)))
                .thenReturn(List.of(movimientoReporte));

        List<MovimientoReporte> movimientos = movimientoUseCase.reportePorClienteYFechas(1, "16/03/2023", "16/03/2023");

        assertThat(movimientos).isNotEmpty();
    }

    @Test
    public void reportePorClienteYFechasError() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movimientoUseCase.reportePorClienteYFechas(1, TEST, TEST);
        });

        Assertions.assertEquals("Formato de fecha no adecuando, el formato debe de ser dd/MM/yyyy", thrown.getMessage());
    }

}
