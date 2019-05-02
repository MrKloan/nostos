package io.fries.nostos.core.vehicle

import net.jqwik.api.Arbitraries
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException

internal class VehicleNumberTest {

    companion object {
        private const val VEHICLE_NUMBER_LENGTH = 6
        private const val ZERO = '0'
    }

    @Property
    internal fun should_create_a_vehicle_number_from_an_alphanumerical_uppercase_string_of_six_characters(@ForAll("valid_numbers") number: String) {
        val vehicleNumber = VehicleNumber(number)
        assertThat(vehicleNumber.number).isEqualTo(number)
    }

    @Provide
    fun valid_numbers() = Arbitraries.strings().alpha().numeric().ofLength(VEHICLE_NUMBER_LENGTH).unique().map(String::toUpperCase)

    @Property
    internal fun should_uppercase_an_alphanumerical_string_of_six_characters(@ForAll("lowercase_numbers") lowerCaseNumber: String) {
        val vehicleNumber = VehicleNumber(lowerCaseNumber)
        assertThat(vehicleNumber.number).isEqualTo(lowerCaseNumber.toUpperCase())
    }

    @Provide
    fun lowercase_numbers() = Arbitraries.strings().alpha().numeric().ofLength(VEHICLE_NUMBER_LENGTH).unique().map(String::toLowerCase)

    @Property
    internal fun should_throw_when_the_number_is_longer_than_its_expected_length(@ForAll("longer_numbers") number: String) {
        assertThatIllegalArgumentException()
                .isThrownBy { VehicleNumber(number) }
                .withMessage("Vehicle number cannot be longer than $VEHICLE_NUMBER_LENGTH")
                .withNoCause()
    }

    @Provide
    fun longer_numbers() = Arbitraries.strings().alpha().numeric().ofMinLength(VEHICLE_NUMBER_LENGTH + 1).unique().map(String::toUpperCase)

    @Property
    internal fun should_pad_on_the_left_with_zeroes_when_the_number_is_shorter_than_its_expected_length(@ForAll("shorter_numbers") number: String) {
        val vehicleNumber = VehicleNumber(number)
        assertThat(vehicleNumber.number).isEqualTo(number.padStart(VEHICLE_NUMBER_LENGTH, ZERO))
    }

    @Provide
    fun shorter_numbers() = Arbitraries.strings().alpha().numeric().ofMaxLength(VEHICLE_NUMBER_LENGTH - 1).unique().map(String::toUpperCase)
}