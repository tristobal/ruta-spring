package cl.ido.ruta.service;

import cl.ido.ruta.aspect.LoggingInfo;
import cl.ido.ruta.aspect.LoggingInfoAspect;
import cl.ido.ruta.domain.Farmacia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmaciasApiService {

    private RestTemplate restTemplate = new RestTemplate();

    private static Logger logger = LogManager.getLogger(LoggingInfoAspect.class);

    @LoggingInfo
    public List<Farmacia> getFarmacias() {
        String url = "http://farmanet.minsal.cl/index.php/ws/getLocales";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String response = responseEntity.getBody();
        return buildFarmaciasList(response);
    }

    private List<Farmacia> buildFarmaciasList(String response) {
        List<Farmacia> farmacias = new ArrayList<>();
        Farmacia farmacia;
        JSONArray jsonArray = new JSONArray(response.substring(1, response.length()));
        for (Object object : jsonArray) {
            try {
                JSONObject jsonObject = (JSONObject) object;
                farmacia = new Farmacia();
                farmacia.setId(jsonObject.get("local_id").toString());
                farmacia.setLocal(jsonObject.get("local_nombre").toString());
                farmacia.setComuna(jsonObject.get("comuna_nombre").toString());
                farmacia.setLocalidad(jsonObject.get("localidad_nombre").toString());
                farmacia.setDireccion(jsonObject.get("local_direccion").toString());
                farmacia.setHoraApertura(jsonObject.get("funcionamiento_hora_apertura").toString());
                farmacia.setHoraCierre(jsonObject.get("funcionamiento_hora_cierre").toString());
                farmacia.setTelefono(jsonObject.get("local_telefono").toString());
                farmacia.setLat(Double.parseDouble( jsonObject.getString("local_lat") ));
                farmacia.setLng(Double.parseDouble( jsonObject.getString("local_lng") ));
                farmacias.add(farmacia);
            } catch (Exception e) {
                logger.warn("Error en construir una farmacia. ERROR: " + e.getMessage());
            }
        }
        return farmacias;
    }
}
