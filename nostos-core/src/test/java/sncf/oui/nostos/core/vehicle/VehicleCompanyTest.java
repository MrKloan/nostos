package sncf.oui.nostos.core.vehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class VehicleCompanyTest {

    @Test
    void should_create_a_vehicle_company() {
        final String aCompany = "aCompany";
        final VehicleCompany theVehicleCompany = VehicleCompany.of(aCompany);
        assertThat(theVehicleCompany.company()).isEqualTo(aCompany);
    }

    @ParameterizedTest
    @ValueSource(strings = {"   aCompany", "aCompany   ", "   aCompany      "})
    void should_trim_the_vehicle_company(final String aCompany) {
        final VehicleCompany theVehicleCompany = VehicleCompany.of(aCompany);

        assertThat(theVehicleCompany.company()).isEqualTo("aCompany");
    }

    @Test
    void should_throw_exception_when_null() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> VehicleCompany.of(null))
                .withMessage("company");
    }

    @Test
    void should_throw_exception_when_empty() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleCompany.of(""))
                .withMessage("Company should not be empty");
    }
}