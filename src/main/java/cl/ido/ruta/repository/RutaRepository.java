package cl.ido.ruta.repository;

import cl.ido.ruta.domain.Place;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RutaRepository extends MongoRepository<Place, Long> {

    Place findByName(String name);

    List<Place> findByLocationWithin(Box box);
}
