package pichincha.cuenta;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pichincha.cliente.Cliente;
import pichincha.cliente.ClienteUseCase;
import pichincha.cuenta.gateway.CuentaClient;
import pichincha.transaccion.Common;
import pichincha.transaccion.RespuestaEnum;

import java.util.List;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class CuentaUseCase {

    private final CuentaClient cuentaClient;
    private final ClienteUseCase clienteUseCase;


    public Common<Cuenta> crearCuenta(Cuenta cuenta){

        if(cuenta== null || cuenta.getCliente().getIdCliente() == null) {
            log.log(Level.SEVERE, "El id del cliente es obligatorio");

            return Common.<Cuenta>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("El id del cliente es obligatorio")
                    .build();
        }

        Common<Cliente> clienteCommon= clienteUseCase.buscarPorId(cuenta.getCliente().getIdCliente());

        if(clienteCommon == null || clienteCommon.getData() == null || clienteCommon.getData().getIdCliente() == null) {
            log.log(Level.SEVERE, "El  cliente suministrado  no existe");
            return Common.<Cuenta>builder()
                    .exito(Boolean.FALSE)
                    .mensaje("El  cliente suministrado  no existe")
                    .build();
        }

        if(cuenta.getEstado() == null){
            cuenta.setEstado(Boolean.TRUE);
        }

        return Common.<Cuenta>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(cuentaClient.crearCuenta(cuenta))
                .build();
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

    public Common<Cuenta> buscarPorId(Integer id){
        return Common.<pichincha.cuenta.Cuenta>builder()
                .exito(Boolean.TRUE)
                .mensaje(RespuestaEnum.EXITO.message)
                .data(cuentaClient.buscarPorId(id))
                .build();
    }

}
