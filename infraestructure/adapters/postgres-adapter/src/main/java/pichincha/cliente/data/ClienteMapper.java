package pichincha.cliente.data;

import pichincha.cliente.Cliente;
import pichincha.persona.Persona;
import pichincha.persona.data.PersonaData;
import pichincha.persona.data.PersonaMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ClienteData toClientData(Cliente cliente) {
        if(cliente == null) {
            return null;
        }

        return ClienteData.builder()
                .idCliente(cliente.getIdCliente())
                .persona(cliente.getPersona() == null || cliente.getPersona().getIdPersona() == null ?
                        PersonaData.builder().build() : PersonaMapper.toPersonaData(cliente.getPersona()))
                .clave(cliente.getClave())
                .estado(cliente.getEstado())
                .build();
    }

    public static List<Cliente> toClient(List<ClienteData> clientesData) {
        if(clientesData == null) {
            return Collections.emptyList();
        }

        List<Cliente> clientes = new ArrayList<>();

        for (ClienteData clienteData : clientesData){
            clientes.add(toClient(clienteData));
        }

        return clientes;
    }

    public static Cliente toClient(ClienteData clienteData) {
        if(clienteData == null) {
            return null;
        }

        return Cliente.builder()
                .idCliente(clienteData.getIdCliente())
                .persona(clienteData.getPersona() == null || clienteData.getPersona().getIdPersona() == null ?
                        Persona.builder().build() : PersonaMapper.toPersona(clienteData.getPersona()))
                .clave(clienteData.getClave())
                .estado(clienteData.getEstado())
                .build();
    }
}
