package sncf.oui.nostos.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class VehicleNumberTest {

    @ParameterizedTest()
    @ValueSource(strings = {"123456", "ABC123", "123DEF", "ABCDEF"})
    void should_return_vehicle_number(final String number) {
        assertThat(VehicleNumber.of(number).number()).isEqualTo(number);
    }

    @Test
    void should_fail_if_more_than_6_chars() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleNumber.of("1234567"))
                .withMessage("Vehicle number should have 6 chars or less");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123.", "123-", "123/", "123?", "*", "12 "})
    void number_containing_special_characters_should_fail(final String number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleNumber.of(number))
                .withMessage("Vehicle number should not contain special character");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "123abc"})
    void should_return_uppercase_vehicle_number(final String number) {
        assertThat(VehicleNumber.of(number).number()).isEqualTo(number.toUpperCase());
    }

    @ParameterizedTest
    @CsvSource({"ABC,000ABC", "123,000123", "9,000009"})
    void should_return_left_padded_vehicle_number_with_0_until_length_6(final String number, final String expectedNumber) {
        assertThat(VehicleNumber.of(number).number()).isEqualTo(expectedNumber);
    }

    @Test
    void should_throw_illegal_argument_exception_when_null_number() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> VehicleNumber.of(null))
                .withMessage("number");
    }

    @Test
    void should_throw_illegal_argument_exception_when_empty_number() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleNumber.of(""))
                .withMessage("Vehicle number should not be empty");
    }
}
