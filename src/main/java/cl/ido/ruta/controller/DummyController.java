package cl.ido.ruta.controller;

import cl.ido.ruta.aspect.LoggingInfo;
import cl.ido.ruta.domain.Farmacia;
import cl.ido.ruta.service.DummyService;
import cl.ido.ruta.service.FarmaciasApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @Autowired
    private FarmaciasApiService farmaciasApiService;

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

    @GetMapping(value = "/farmacias", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @LoggingInfo
    public List<Farmacia> getFarmacias() {
        return farmaciasApiService.getFarmacias();
    }

}
