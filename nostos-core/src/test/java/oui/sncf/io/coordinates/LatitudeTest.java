package oui.sncf.io.coordinates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LatitudeTest {

    @ParameterizedTest
    @ValueSource(doubles = {-91., -90., -100., 90., 91., 100.})
    void out_of_range_degrees_should_throw_exception(final double degrees) {

        assertThatThrownBy(() -> Latitude.of(degrees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("latitude should be between -90° and 90°");
    }

    @ParameterizedTest
    @ValueSource(doubles = {-89., 0., 89.})
    void should_return_valid_latitude(final double degrees) {
        final Latitude latitude = Latitude.of(degrees);

        assertThat(latitude.getDegrees()).isEqualTo(degrees);
    }
}
