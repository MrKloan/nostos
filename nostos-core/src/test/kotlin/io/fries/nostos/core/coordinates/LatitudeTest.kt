package io.fries.nostos.core.coordinates

import net.jqwik.api.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException

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

    @Property
    internal fun should_throw_when_the_degrees_are_out_of_bounds(@ForAll("out_of_bounds_degrees") degrees: Double) {
        assertThatIllegalArgumentException()
                .isThrownBy { Latitude(degrees) }
                .withMessage("Latitude degrees should be in the range [$MIN_DEGREES, $MAX_DEGREES]")
                .withNoCause()
    }

    @Provide
    fun out_of_bounds_degrees(): Arbitrary<Double> {
        val lowerDegrees = Arbitraries.doubles().lessOrEqual(MIN_DEGREES).filter { degrees -> degrees != MIN_DEGREES }.unique()
        val upperDegrees = Arbitraries.doubles().greaterOrEqual(MAX_DEGREES).filter { degrees -> degrees != MAX_DEGREES }.unique()

        return Arbitraries.oneOf(lowerDegrees, upperDegrees)
    }
}