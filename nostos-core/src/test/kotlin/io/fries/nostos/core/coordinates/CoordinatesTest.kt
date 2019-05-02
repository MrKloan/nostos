package io.fries.nostos.core.coordinates

import net.jqwik.api.ForAll
import net.jqwik.api.Property
import org.assertj.core.api.Assertions.assertThat

internal class CoordinatesTest {

    @Property
    internal fun should_create_coordinates_from_degrees(
            @ForAll @LatitudeDegrees latitude: Double,
            @ForAll @LongitudeDegrees longitude: Double
    ) {
        val coordinates = Coordinates.of(latitude, longitude)

        assertThat(coordinates.latitude).isEqualTo(latitude)
        assertThat(coordinates.longitude).isEqualTo(longitude)
    }
}