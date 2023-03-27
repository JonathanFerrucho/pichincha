package pichincha.cuenta;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import pichincha.cuenta.data.CuentaData;
import pichincha.cuenta.data.CuentaMapper;
import pichincha.cuenta.gateway.CuentaClient;
import pichincha.cuenta.repository.CuentaRepository;
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
        return CuentaMapper.toCuenta(cuentaRepository.save(CuentaMapper.toCuentaData(cuenta)));
    }

    @Override
    public void eliminarCuenta(Integer id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> getCuenta(Cuenta cuenta) {
        Example<CuentaData> example= Example.of(CuentaMapper.toCuentaData(cuenta));
        return CuentaMapper.toCuenta(cuentaRepository.findAll(example));
    }

    @Override
    public Cuenta buscarPorId(Integer id) {
        Optional<CuentaData> optCuentaData = cuentaRepository.findById(id);
        return CuentaMapper.toCuenta(optCuentaData.isPresent() ?
                optCuentaData.get() : CuentaData.builder().build());
    }
}