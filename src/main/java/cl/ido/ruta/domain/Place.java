package cl.ido.ruta.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ruta")
public class Place {

    @Id
    public String id;

    public String name;

    public String notes;

    public boolean visited;

    public String address;
}
