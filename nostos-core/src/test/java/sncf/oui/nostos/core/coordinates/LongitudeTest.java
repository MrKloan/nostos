package sncf.oui.nostos.core.coordinates;

import net.jqwik.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LongitudeTest {

    @Property
    void out_of_range_degrees_should_throw_exception(@ForAll("invalidLongitudes") final double degrees) {

        assertThatThrownBy(() -> Longitude.of(degrees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("longitude should be between -180° and 180°");
    }

    @Property
    void should_return_valid_longitude(@ForAll("validLongitudes") final double degrees) {
        final Longitude longitude = Longitude.of(degrees);

        assertThat(longitude.degrees()).isEqualTo(degrees);
    }

    @Provide
    Arbitrary<Double> invalidLongitudes() {
        final Arbitrary<Double> lowerBounds = Arbitraries.doubles().lessOrEqual(-180.).unique();
        final Arbitrary<Double> upperBounds = Arbitraries.doubles().greaterOrEqual(180.).unique();

        return Arbitraries.oneOf(lowerBounds, upperBounds);
    }

    @Provide
    Arbitrary<Double> validLongitudes() {
        return Arbitraries.doubles()
                .between(-180., 180.)
                .filter(latitude -> latitude != -180.)
                .filter(latitude -> latitude != 180.)
                .unique();
    }
}
