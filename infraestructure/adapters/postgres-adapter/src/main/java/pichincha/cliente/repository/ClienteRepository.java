package pichincha.cliente.repository;

import pichincha.cliente.data.ClienteData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteData, Integer> {
}
