package sncf.oui.nostos.core.coordinates;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
abstract class Latitude {

    private static final double MIN_LATITUDE = -90.;
    private static final double MAX_LATITUDE = 90.;

    static Latitude of(final double degrees) {
        return ImmutableLatitude.of(degrees);
    }

    @Parameter
    abstract double degrees();

    @Check
    void check() {
        if (degrees() <= MIN_LATITUDE || degrees() >= MAX_LATITUDE) {
            throw new IllegalArgumentException("latitude should be between -90° and 90°");
        }
    }
}
