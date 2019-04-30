package io.fries.nostos.core.coordinates

import net.jqwik.api.*
import org.assertj.core.api.Assertions.assertThat

class LongitudeTest {

    companion object {
        private const val MIN_DEGREES = -180.0
        private const val MAX_DEGREES = 180.0
    }

    @Property
    internal fun should_create_a_longitude_with_valid_degrees(@ForAll("valid_degrees") degrees: Double) {
        val longitude = Longitude(degrees)

        assertThat(longitude).isNotNull
        assertThat(longitude.degrees).isEqualTo(degrees)
    }

    @Provide
    fun valid_degrees(): Arbitrary<Double> {
        return Arbitraries.doubles().between(MIN_DEGREES, MAX_DEGREES).unique()
    }
}