package cl.ido.ruta.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

	private static Logger logger = LogManager.getLogger(DummyService.class);

    public String getDummyMessage() {
    	logger.info("DummyService.getDummyMessage");
        return "Hello World";
    }
}
