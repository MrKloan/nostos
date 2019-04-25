package sncf.oui.nostos.core.coordinates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinatesTest {


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {"0.;0.", "-89.;0.", "89.;0.", "0.;-179.", "0.;179."})
    void should_return_latitude_and_longitude(final double latitude, final double longitude) {
        final Coordinates coordinates = Coordinates.of(latitude, longitude);
        assertThat(coordinates.getLatitude()).isEqualTo(latitude);
        assertThat(coordinates.getLongitude()).isEqualTo(longitude);
    }

}
