package cl.ido.ruta.domain;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Location {

    @GeoSpatialIndexed
    private double[] coordinates;

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
