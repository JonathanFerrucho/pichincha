package pichincha.cliente.gateway;

import pichincha.cliente.Cliente;

import java.util.List;

public interface ClienteClient {


     Cliente crearCliene(Cliente cliente);
     void eliminarCliente(Integer id);
     List<Cliente> getCliente(Cliente cliente);
     Cliente buscarPorId(Integer id);
}
