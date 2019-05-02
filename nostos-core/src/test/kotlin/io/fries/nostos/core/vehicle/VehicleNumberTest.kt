package io.fries.nostos.core.vehicle

import net.jqwik.api.Arbitraries
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class VehicleNumberTest {

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 6
        private const val ZERO = '0'
    }

    @Property
    internal fun should_create_a_vehicle_number_from_an_alphanumerical_uppercase_string_of_six_characters(@ForAll("valid_numbers") number: String) {
        val vehicleNumber = VehicleNumber(number)
        assertThat(vehicleNumber.number).isEqualTo(number)
    }

    @Provide
    fun valid_numbers() = Arbitraries.strings().alpha().numeric().ofLength(MAX_LENGTH).unique().map(String::toUpperCase)

    @Property
    internal fun should_uppercase_an_alphanumerical_string_of_six_characters(@ForAll("lowercase_numbers") lowerCaseNumber: String) {
        val vehicleNumber = VehicleNumber(lowerCaseNumber)
        assertThat(vehicleNumber.number).isEqualTo(lowerCaseNumber.toUpperCase())
    }

    @Provide
    fun lowercase_numbers() = Arbitraries.strings().alpha().numeric().ofLength(MAX_LENGTH).unique().map(String::toLowerCase)

    @Property
    internal fun should_throw_when_the_number_is_longer_than_its_expected_length(@ForAll("longer_numbers") number: String) {
        assertThatIllegalArgumentException()
                .isThrownBy { VehicleNumber(number) }
                .withMessage("Vehicle number '$number' should be an alphanumerical string between $MIN_LENGTH and $MAX_LENGTH characters")
                .withNoCause()
    }

    @Provide
    fun longer_numbers() = Arbitraries.strings().alpha().numeric().ofMinLength(MAX_LENGTH + 1).unique().map(String::toUpperCase)

    @Property
    internal fun should_pad_on_the_left_with_zeroes_when_the_number_is_shorter_than_its_expected_length(@ForAll("shorter_numbers") number: String) {
        val vehicleNumber = VehicleNumber(number)
        assertThat(vehicleNumber.number).isEqualTo(number.padStart(MAX_LENGTH, ZERO))
    }

    @Provide
    fun shorter_numbers() = Arbitraries.strings().alpha().numeric().ofMinLength(MIN_LENGTH).ofMaxLength(MAX_LENGTH - 1).unique().map(String::toUpperCase)

    @Test
    internal fun should_throw_when_the_number_is_empty() {
        assertThatIllegalArgumentException()
                .isThrownBy { VehicleNumber("") }
                .withMessage("Vehicle number '' should be an alphanumerical string between $MIN_LENGTH and $MAX_LENGTH characters")
                .withNoCause()
    }

    @Property
    internal fun should_throw_when_the_number_contains_non_alphanumerical_characters(@ForAll("numbers_with_invalid_characters") number: String) {
        assertThatIllegalArgumentException()
                .isThrownBy { VehicleNumber(number) }
                .withMessage("Vehicle number '$number' should be an alphanumerical string between $MIN_LENGTH and $MAX_LENGTH characters")
                .withNoCause()
    }

    @Provide
    fun numbers_with_invalid_characters() = Arbitraries.strings().ascii().whitespace().ofLength(MAX_LENGTH).unique().map(String::toUpperCase)
}