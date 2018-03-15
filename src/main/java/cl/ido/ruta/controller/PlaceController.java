package cl.ido.ruta.controller;

import cl.ido.ruta.aspect.LoggingInfo;
import cl.ido.ruta.domain.Place;
import cl.ido.ruta.repository.RutaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/place/box/{xMin},{yMin},{xMax},{yMax}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Retorna todos los lugares que calcen en las coordenadas dadas",
            notes = "Devuelve un JSON con todos los Places que estén dentro del Box conformado por las coordenadas ingresadas",
            response = List.class
    )
    @LoggingInfo
    public List<Place> findByBox(@PathVariable(value = "xMin") Double xMin,
                                 @PathVariable(value = "yMin") Double yMin,
                                 @PathVariable(value = "xMax") Double xMax,
                                 @PathVariable(value = "yMax") Double yMax) {
        Box box = new Box( new Point(xMin, yMin), new Point(xMax, yMax));
        return rutaRepository.findByLocation_CoordinatesWithin(box);
    }

    @PostMapping(value = "/place",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Crea un lugar",
            notes = "Crea y devuelve un Place de acuerdo a los datos ingresados",
            response = Place.class
    )
    @LoggingInfo
    public Place createPlace(@RequestBody Place place) {
        return rutaRepository.save(place);
    }

    @DeleteMapping(value = "/place/{id}")
    @ApiOperation(
            value = "Borra un lugar",
            notes = "Elimina un Place de acuerdo a su ID",
            response = Place.class
    )
    @LoggingInfo
    public void deletePlace(@PathVariable(value = "id") String id) {
        rutaRepository.delete(id);
    }
}
