package io.fries.nostos.core.coordinates

import net.jqwik.api.*
import org.assertj.core.api.Assertions.assertThat

class LatitudeTest {

    companion object {
        private const val MIN_DEGREES = -90.0
        private const val MAX_DEGREES = 90.0
    }

    @Property
    internal fun should_create_a_latitude_with_valid_degrees(@ForAll("valid_degrees") degrees: Double) {
        val latitude = Latitude(degrees)

        assertThat(latitude).isNotNull
        assertThat(latitude.degrees).isEqualTo(degrees)
    }

    @Provide
    fun valid_degrees(): Arbitrary<Double> {
        return Arbitraries.doubles().between(MIN_DEGREES, MAX_DEGREES).unique()
    }
}