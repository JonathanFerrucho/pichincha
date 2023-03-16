package pichincha.cuenta;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.Cliente;
import pichincha.cliente.ClienteUseCase;
import pichincha.cuenta.gateway.CuentaClient;

import java.util.List;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class CuentaUseCase {

    private final CuentaClient cuentaClient;
    private final ClienteUseCase clienteUseCase;


    public Cuenta crearCuenta(Cuenta cuenta){

        if(cuenta.getCliente() == null || cuenta.getCliente().getIdCliente() == null) {
            log.log(Level.SEVERE, "El id del cliente es obligatorio");
            throw  new IllegalArgumentException("El id del cliente es obligatorio");
        }

        Cliente cliente= clienteUseCase.buscarPorId(cuenta.getCliente().getIdCliente());

        if(cliente == null || cliente.getIdCliente() == null) {
            log.log(Level.SEVERE, "El  cliente suministrado  no existe");
            throw  new IllegalArgumentException("El  cliente suministrado  no existe");
        }

        if(cuenta.getEstado() == null){
            cuenta.setEstado(Boolean.TRUE);
        }

        return cuentaClient.crearCuenta(cuenta);
    }

    public Cuenta ModificarCuenta(Cuenta cuenta){

        return cuentaClient.crearCuenta(cuenta);
    }

    public void eliminarCuenta(Integer id){
        cuentaClient.eliminarCuenta(id);
    }

    public List<Cuenta> getCuenta(Cuenta cuenta){
        return cuentaClient.getCuenta(cuenta);
    }

    public Cuenta buscarPorId(Integer id){
        return cuentaClient.buscarPorId(id);
    }

}
