package pichincha.cliente;

import pichincha.cliente.data.ClienteData;
import pichincha.cliente.data.ClienteMapper;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class ClienteAdapter implements ClienteClient {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return ClienteMapper.toClient(clienteRepository.save(ClienteMapper.toClientData(cliente)));
    }

    @Override
    public void eliminarCliente(Integer id){
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> getCliente(Cliente cliente) {

        Example<ClienteData> example= Example.of(ClienteMapper.toClientData(cliente));
        return ClienteMapper.toClient(clienteRepository.findAll(example));
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        Optional<ClienteData> optClienteData = clienteRepository.findById(id);
        return ClienteMapper.toClient(optClienteData.isPresent() ?
                optClienteData.get() : ClienteData.builder().build());
    }
}