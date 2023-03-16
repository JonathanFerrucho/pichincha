package pichincha.cuenta;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import pichincha.cliente.Cliente;
import pichincha.cliente.ClienteAdapter;
import pichincha.cliente.data.ClienteData;
import pichincha.cuenta.data.CuentaData;
import pichincha.cuenta.gateway.CuentaClient;
import pichincha.cuenta.repository.CuentaRepository;
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
public class CuentaAdapter implements CuentaClient {

    @Autowired
    private final CuentaRepository cuentaRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return toCuenta(cuentaRepository.save(toCuentaData(cuenta)));
    }

    @Override
    public void eliminarCuenta(Integer id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> getCuenta(Cuenta cuenta) {
        Example<CuentaData> example= Example.of(toCuentaData(cuenta));
        return toCuenta(cuentaRepository.findAll(example));
    }

    @Override
    public Cuenta buscarPorId(Integer id) {
        Optional<CuentaData> optCuentaData = cuentaRepository.findById(id);
        return toCuenta(optCuentaData.isPresent() ?
                optCuentaData.get() : CuentaData.builder().build());
    }

    public static CuentaData toCuentaData(Cuenta cuenta) {
        if(cuenta == null) {
            return null;
        }

        return CuentaData.builder()
                .idCuenta(cuenta.getIdCuenta())
                .cliente(ClienteAdapter.toClientData(cuenta.getCliente()))
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
                .cliente(ClienteAdapter.toClient(cuentaData.getCliente()))
                .numeroCuenta(cuentaData.getNumeroCuenta())
                .tipoCuenta(cuentaData.getTipoCuenta())
                .saldoInicial(cuentaData.getSaldoInicial())
                .estado(cuentaData.getEstado())
                .build();
    }
}