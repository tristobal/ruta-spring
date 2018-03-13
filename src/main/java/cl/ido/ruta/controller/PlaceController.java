package cl.ido.ruta.controller;

import cl.ido.ruta.aspect.LoggingInfo;
import cl.ido.ruta.domain.Place;
import cl.ido.ruta.repository.RutaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaceController {

    private RutaRepository rutaRepository;

    @Autowired
    public PlaceController(RutaRepository repo) {
        this.rutaRepository = repo;
    }

    @GetMapping(value = "/place/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Retorna un lugar",
            notes = "Devuelve un JSON con un Place como respuesta que coincida con el parámetro ingresado",
            response = Place.class
    )
    @LoggingInfo
    public Place findOneByName(@PathVariable(value = "name") String name) {
        return rutaRepository.findByName(name);
    }

    @GetMapping(value = "/place", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Retorna un lugar",
            notes = "Devuelve un JSON con todos los Places",
            response = List.class
    )
    @LoggingInfo
    public List<Place> findAll() {
        return rutaRepository.findAll();
    }


    @GetMapping(value = "/placebox", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Retorna un lugar",
            notes = "Devuelve un JSON con todos los Places",
            response = List.class
    )
    @LoggingInfo
    public List<Place> findByBox() {
        Box box = new Box( new Point(-33.438119, -70.636826), new Point(-33.432787,-70.634188));
        return rutaRepository.findByLocation_CoordinatesWithin(box);
    }

}
