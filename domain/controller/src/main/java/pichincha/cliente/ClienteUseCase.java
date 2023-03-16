package pichincha.cliente;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.persona.PersonaUseCase;

import java.util.List;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class ClienteUseCase {

    private final ClienteClient clienteClient;
    private final PersonaUseCase personaUseCase;


    public Cliente crearCliente(Cliente cliente){

        if(cliente.getPersona() == null || cliente.getPersona().getIdentificacion() == null) {
            log.log(Level.SEVERE, "el nombre y la identificacion de la persona es obligatorio");
            throw  new IllegalArgumentException("el nombre y la identificacion de la persona es obligatorio");
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
