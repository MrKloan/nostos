package io.fries.nostos.core.coordinates

import net.jqwik.api.Arbitraries
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.constraints.DoubleRange
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@DoubleRange(min = LatitudeTest.MIN_DEGREES, max = LatitudeTest.MAX_DEGREES)
internal annotation class LatitudeDegrees

internal class LatitudeTest {

    companion object {
        internal const val MIN_DEGREES = -90.0
        internal const val MAX_DEGREES = 90.0
    }

    @Property
    internal fun should_create_a_latitude_with_valid_degrees(@ForAll @LatitudeDegrees degrees: Double) {
        val latitude = Latitude(degrees)

        assertThat(latitude).isNotNull
        assertThat(latitude.degrees).isEqualTo(degrees)
    }

    @Property
    internal fun should_throw_when_the_degrees_are_out_of_range(@ForAll("out_of_range_degrees") degrees: Double) {
        assertThatIllegalArgumentException()
                .isThrownBy { Latitude(degrees) }
                .withMessage("Latitude degrees should be in the range [$MIN_DEGREES, $MAX_DEGREES]")
                .withNoCause()
    }

    @Provide
    fun out_of_range_degrees() = Arbitraries.oneOf(
            Arbitraries.doubles().lessOrEqual(MIN_DEGREES).filter { degrees -> degrees != MIN_DEGREES }.unique(),
            Arbitraries.doubles().greaterOrEqual(MAX_DEGREES).filter { degrees -> degrees != MAX_DEGREES }.unique()
    )
}