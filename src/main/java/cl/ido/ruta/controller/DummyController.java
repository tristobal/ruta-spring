package cl.ido.ruta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class DummyController {

    @RequestMapping(value = "/helloworld")
    public Map<String, String> getHelloWorld() {
        return Collections.singletonMap("response", "Hello World");
    }

}
