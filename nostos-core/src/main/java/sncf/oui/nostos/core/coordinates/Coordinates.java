package sncf.oui.nostos.core.coordinates;

public class Coordinates {

    private final Latitude latitude;
    private final Longitude longitude;

    private Coordinates(final Latitude latitude, final Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Coordinates of(final double latitude, final double longitude) {
        return new Coordinates(Latitude.of(latitude), Longitude.of(longitude));
    }

    public double getLatitude() {
        return latitude.degrees();
    }

    public double getLongitude() {
        return longitude.degrees();
    }
}
