package design.patterns.spring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository //nao e necessario essa anotacao pois o java ja entende que isso [e um repository
public interface ClienteRepository extends CrudRepository<Cliente, UUID> {


}
