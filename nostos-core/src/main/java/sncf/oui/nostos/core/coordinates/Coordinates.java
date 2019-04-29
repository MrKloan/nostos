package sncf.oui.nostos.core.coordinates;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
abstract class Coordinates {

    public static Coordinates of(final double latitude, final double longitude) {
        return ImmutableCoordinates.of(Latitude.of(latitude), Longitude.of(longitude));
    }

    @Parameter
    abstract Latitude latitude();

    @Parameter
    abstract Longitude longitude();

    public double getLatitude() {
        return latitude().degrees();
    }

    public double getLongitude() {
        return longitude().degrees();
    }
}
