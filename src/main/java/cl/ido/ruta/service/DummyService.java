package cl.ido.ruta.service;

import cl.ido.ruta.aspect.LoggingInfo;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

	@LoggingInfo
    public String getDummyMessage() {
        return "Hello World";
    }
}
