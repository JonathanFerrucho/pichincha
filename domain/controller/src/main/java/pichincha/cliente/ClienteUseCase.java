package pichincha.cliente;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.persona.PersonaUseCase;

import java.util.List;

@RequiredArgsConstructor
@Log
public class ClienteUseCase {

    private final ClienteClient clienteClient;
    private final PersonaUseCase personaUseCase;


    public Cliente crearCliente(Cliente cliente){

        if(cliente== null) {
            throw  new IllegalArgumentException("el cliente es obligatoria");
        }

        cliente.setPersona(Boolean.TRUE.equals(personaUseCase.existePersona(cliente.getPersona())) ?
                personaUseCase.buscarPersonaPoridentificacion(cliente.getPersona().getIdentificacion()) :
                personaUseCase.crearPersona(cliente.getPersona()));

        if(cliente.getEstado() == null){
            cliente.setEstado(Boolean.TRUE);
        }

        return clienteClient.crearCliene(cliente);
    }

    public void eliminarCliente(Integer id){
        clienteClient.eliminarCliente(id);
    }


    public Cliente modificarCliente(Cliente cliente){

        return clienteClient.crearCliene(cliente);
    }

    public List<Cliente> getCliente(Cliente cliente){
        return clienteClient.getCliente(cliente);
    }

    public Cliente buscarPorId(Integer id){
        return clienteClient.buscarPorId(id);
    }

}
