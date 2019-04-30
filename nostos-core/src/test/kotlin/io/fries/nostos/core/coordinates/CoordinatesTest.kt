package io.fries.nostos.core.coordinates

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CoordinatesTest {

    @Test
    internal fun should_create_coordinates_from_degrees() {
        val latitude = 1.0
        val longitude = 3.0
        val coordinates = Coordinates.of(latitude, longitude)

        assertThat(coordinates.latitude).isEqualTo(latitude)
        assertThat(coordinates.longitude).isEqualTo(longitude)
    }
}