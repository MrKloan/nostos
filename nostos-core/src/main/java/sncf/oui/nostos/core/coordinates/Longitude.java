package sncf.oui.nostos.core.coordinates;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
abstract class Longitude {

    private static final double MAX_LONGITUDE = 180.;
    private static final double MIN_LATITUDE = -180.;

    static Longitude of(final double degrees) {
        return ImmutableLongitude.of(degrees);
    }

    @Parameter
    abstract double degrees();

    @Check
    void check() {
        if (degrees() <= MIN_LATITUDE || degrees() >= MAX_LONGITUDE) {
            throw new IllegalArgumentException("longitude should be between -180° and 180°");
        }
    }
}
