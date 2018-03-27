package cl.ido.ruta.service;

import cl.ido.ruta.aspect.LoggingInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FarmaciasApiService {

    private RestTemplate restTemplate = new RestTemplate();

    @LoggingInfo
    public String getFarmacias() {
        String url = "http://farmanet.minsal.cl/index.php/ws/getLocales";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String response = responseEntity.getBody();
        return response;
    }
}
