package sncf.oui.nostos.core.coordinates;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
abstract class Latitude {

    static Latitude of(final double degrees) {
        return ImmutableLatitude.of(degrees);
    }

    @Parameter
    abstract double degrees();

    @Check
    void check() {
        if (degrees() <= -90 || degrees() >= 90) {
            throw new IllegalArgumentException("latitude should be between -90° and 90°");
        }
    }
}
