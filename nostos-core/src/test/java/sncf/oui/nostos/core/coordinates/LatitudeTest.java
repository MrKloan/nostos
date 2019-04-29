package sncf.oui.nostos.core.coordinates;

import net.jqwik.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LatitudeTest {

    @Property
    void out_of_range_degrees_should_throw_exception(@ForAll("invalidLatitudes") final double degrees) {
        assertThatThrownBy(() -> Latitude.of(degrees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("latitude should be between -90° and 90°");
    }

    @Property
    void should_return_valid_latitude(@ForAll("validLatitudes") final double degrees) {
        final Latitude latitude = Latitude.of(degrees);

        assertThat(latitude.degrees()).isEqualTo(degrees);
    }

    @Provide
    Arbitrary<Double> invalidLatitudes() {
        final Arbitrary<Double> lowerBounds = Arbitraries.doubles().lessOrEqual(-90.).unique();
        final Arbitrary<Double> upperBounds = Arbitraries.doubles().greaterOrEqual(90.).unique();

        return Arbitraries.oneOf(lowerBounds, upperBounds);
    }

    @Provide
    Arbitrary<Double> validLatitudes() {
        return Arbitraries.doubles()
                .between(-90., 90.)
                .filter(latitude -> latitude != -90.)
                .filter(latitude -> latitude != 90.)
                .unique();
    }
}
