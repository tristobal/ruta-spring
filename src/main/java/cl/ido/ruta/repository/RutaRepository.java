package cl.ido.ruta.repository;

import cl.ido.ruta.domain.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RutaRepository extends MongoRepository<Place, Long> {
    Place findByName(String name);
}
