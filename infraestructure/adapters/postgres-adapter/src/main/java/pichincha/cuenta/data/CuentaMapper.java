package pichincha.cuenta.data;

import pichincha.cliente.data.ClienteMapper;
import pichincha.cuenta.Cuenta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuentaMapper {

    private CuentaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CuentaData toCuentaData(Cuenta cuenta) {
        if(cuenta == null) {
            return null;
        }

        return CuentaData.builder()
                .idCuenta(cuenta.getIdCuenta())
                .cliente(ClienteMapper.toClientData(cuenta.getCliente()))
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .build();
    }

    public static List<Cuenta> toCuenta(List<CuentaData> cuentasData) {
        if(cuentasData == null) {
            return Collections.emptyList();
        }

        List<Cuenta> cuentas = new ArrayList<>();

        for (CuentaData cuentaData : cuentasData){
            cuentas.add(toCuenta(cuentaData));
        }

        return cuentas;
    }

    public static Cuenta toCuenta(CuentaData cuentaData) {
        if(cuentaData == null) {
            return null;
        }

        return Cuenta.builder()
                .idCuenta(cuentaData.getIdCuenta())
                .cliente(ClienteMapper.toClient(cuentaData.getCliente()))
                .numeroCuenta(cuentaData.getNumeroCuenta())
                .tipoCuenta(cuentaData.getTipoCuenta())
                .saldoInicial(cuentaData.getSaldoInicial())
                .estado(cuentaData.getEstado())
                .build();
    }
}
