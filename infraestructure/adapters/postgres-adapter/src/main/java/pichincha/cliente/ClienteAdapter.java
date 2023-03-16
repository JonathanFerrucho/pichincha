package pichincha.cliente;

import pichincha.cliente.data.ClienteData;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import pichincha.persona.Persona;
import pichincha.persona.PersonaAdapter;
import pichincha.persona.data.PersonaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class ClienteAdapter implements ClienteClient {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliene(Cliente cliente) {
        return toClient(clienteRepository.save(toClientData(cliente)));
    }

    @Override
    public void eliminarCliente(Integer id){
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> getCliente(Cliente cliente) {

        Example<ClienteData> example= Example.of(toClientData(cliente));
        return toClient(clienteRepository.findAll(example));
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        Optional<ClienteData> optClienteData = clienteRepository.findById(id);
        return toClient(optClienteData.isPresent() ?
                optClienteData.get() : ClienteData.builder().build());
    }


    public static ClienteData toClientData(Cliente cliente) {
        if(cliente == null) {
            return null;
        }

        return ClienteData.builder()
                .idCliente(cliente.getIdCliente())
                .persona(cliente.getPersona() == null || cliente.getPersona().getIdPersona() == null ?
                        PersonaData.builder().build() : PersonaAdapter.toPersonaData(cliente.getPersona()))
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
                        Persona.builder().build() : PersonaAdapter.toPersona(clienteData.getPersona()))
                .clave(clienteData.getClave())
                .estado(clienteData.getEstado())
                .build();
    }
}