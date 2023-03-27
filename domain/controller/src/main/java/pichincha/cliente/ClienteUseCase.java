package pichincha.cliente;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.gateway.ClienteClient;
import pichincha.persona.PersonaUseCase;
import pichincha.transaccion.Common;
import pichincha.transaccion.RespuestaEnum;

import java.util.List;

@RequiredArgsConstructor
@Log
public class ClienteUseCase {

    private final ClienteClient clienteClient;
    private final PersonaUseCase personaUseCase;


    public Common<Cliente> crearCliente(Cliente cliente){
        if(cliente== null) {
            return  Common.<Cliente>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("el cliente es obligatoria")
                    .build();
        }

        cliente.setPersona(Boolean.TRUE.equals(personaUseCase.existePersona(cliente.getPersona())) ?
                personaUseCase.buscarPersonaPoridentificacion(cliente.getPersona().getIdentificacion()) :
                personaUseCase.crearPersona(cliente.getPersona()).getData());

        if(cliente.getEstado() == null){
            cliente.setEstado(Boolean.TRUE);
        }

        return Common.<Cliente>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(clienteClient.crearCliente(cliente))
                .build();
    }

    public void eliminarCliente(Integer id){
        clienteClient.eliminarCliente(id);
    }


    public Cliente modificarCliente(Cliente cliente){

        return clienteClient.crearCliente(cliente);
    }

    public List<Cliente> getCliente(Cliente cliente){
        return clienteClient.getCliente(cliente);
    }

    public Common<Cliente> buscarPorId(Integer id){
        return Common.<Cliente>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(clienteClient.buscarPorId(id))
                .build();
    }

}
