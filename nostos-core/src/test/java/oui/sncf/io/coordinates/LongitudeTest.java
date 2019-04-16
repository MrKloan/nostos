package oui.sncf.io.coordinates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LongitudeTest {

    @ParameterizedTest
    @ValueSource(doubles = {-181., -180., 180., 181., 200.})
    void out_of_range_degrees_should_throw_exception(final double degrees) {

        assertThatThrownBy(() -> Longitude.of(degrees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("longitude should be between -180° and 180°");
    }

    @ParameterizedTest
    @ValueSource(doubles = {-179., 0., 179.})
    void should_return_valid_latitude(final double degrees) {
        final Longitude longitude = Longitude.of(degrees);

        assertThat(longitude.getDegrees()).isEqualTo(degrees);
    }
}
