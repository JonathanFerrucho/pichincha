package pichincha;

import pichincha.cuenta.CuentaUseCase;
import pichincha.cuenta.gateway.CuentaClient;
import pichincha.movimiento.MovimientoUseCase;
import pichincha.movimiento.gateway.MovimientoClient;
import pichincha.movimiento.gateway.MovimientoReportesClient;
import pichincha.persona.PersonaUseCase;
import pichincha.persona.gateway.PersonaClient;
import pichincha.cliente.ClienteUseCase;
import pichincha.cliente.gateway.ClienteClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PersonaUseCase personaUseCase(PersonaClient personaClient) {
        return new PersonaUseCase(personaClient);
    }

    @Bean
    public ClienteUseCase clienteUseCase(ClienteClient clienteClient, PersonaUseCase personaUseCase) {
        return new ClienteUseCase(clienteClient, personaUseCase);
    }

    @Bean
    public CuentaUseCase cuentaUseCase(CuentaClient cuentaClient, ClienteUseCase clienteUseCase) {
        return new CuentaUseCase(cuentaClient, clienteUseCase);
    }

    @Bean
    public MovimientoUseCase movimientoUseCase(MovimientoClient movimientoClient,
                                               MovimientoReportesClient movimientoReportesClient, CuentaUseCase cuentaUseCase) {
        return new MovimientoUseCase(movimientoClient, movimientoReportesClient, cuentaUseCase);
    }
}
