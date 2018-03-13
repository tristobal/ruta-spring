package cl.ido.ruta.controller;

import cl.ido.ruta.aspect.LoggingInfo;
import cl.ido.ruta.domain.Place;
import cl.ido.ruta.repository.RutaRepository;
import cl.ido.ruta.service.DummyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.Map;

@RestController
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @Autowired
    private RutaRepository rutaRepository;

    @RequestMapping(value = "/helloworld",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Endpoint de prueba",
            notes = "Devuelve un JSON con 'Hello World' como respuesta",
            response = Map.class
    )
    @LoggingInfo
    public Map<String, String> getHelloWorld() {
        return Collections.singletonMap("response", dummyService.getDummyMessage());
    }


    @GetMapping(value = "/place/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @LoggingInfo
    public Place findOneByName(@PathVariable(value = "name") String name) {
        return rutaRepository.findByName(name);
    }

}
