package pichincha.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pichincha.cuenta.data.CuentaData;

public interface CuentaRepository extends JpaRepository<CuentaData, Integer> {
}
