package pichincha.cuenta.gateway;

import pichincha.cuenta.Cuenta;

import java.util.List;

public interface CuentaClient {

     Cuenta crearCuenta(Cuenta cuenta);
     void eliminarCuenta(Integer id);
     List<Cuenta> getCuenta(Cuenta cuenta);
     Cuenta buscarPorId(Integer id);
}
