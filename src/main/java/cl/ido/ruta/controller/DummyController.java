package cl.ido.ruta.controller;

import cl.ido.ruta.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @RequestMapping(value = "/helloworld")
    public Map<String, String> getHelloWorld() {
        return Collections.singletonMap("response", dummyService.getDummyMessage());
    }

}
