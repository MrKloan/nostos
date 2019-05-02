package io.fries.nostos.core.vehicle

import io.fries.nostos.core.vehicle.VehicleNumberTest.Companion.VEHICLE_NUMBER_LENGTH
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.constraints.NumericChars
import net.jqwik.api.constraints.StringLength
import net.jqwik.api.constraints.UpperChars
import org.assertj.core.api.Assertions.assertThat

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@UpperChars
@NumericChars
@StringLength(VEHICLE_NUMBER_LENGTH)
internal annotation class VehicleNumbers

internal class VehicleNumberTest {

    companion object {
        internal const val VEHICLE_NUMBER_LENGTH = 6
    }

    @Property
    internal fun should_create_a_vehicle_number_from_an_alphanumerical_uppercase_string_of_six_characters(@ForAll @VehicleNumbers number: String) {
        val vehicleNumber = VehicleNumber(number)
        assertThat(vehicleNumber.number).isEqualTo(number)
    }
}