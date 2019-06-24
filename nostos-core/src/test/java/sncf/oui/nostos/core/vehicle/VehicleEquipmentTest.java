package sncf.oui.nostos.core.vehicle;

import net.jqwik.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class VehicleEquipmentTest {

    @Property
    void should_create_vehicle_equipment(@ForAll("validVehicleEquipments") String validVehicleEquipment) {
        assertThat(
                VehicleEquipment.of(validVehicleEquipment).equipment()
        ).isEqualTo(validVehicleEquipment);
    }

    @Provide
    Arbitrary<String> validVehicleEquipments() {
        return Arbitraries.strings()
                .alpha()
                .ofLength(3)
                .unique()
                .map(String::toUpperCase);
    }

    @Property
    void should_throw_exception_when_vehicle_equipment_length_is_invalid(
            @ForAll("vehicleEquipmentsWithInvalidLength") String vehicleEquipmentLengthInvalid
    ) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleEquipment.of(vehicleEquipmentLengthInvalid))
                .withMessageContaining("Equipment is invalid: not 3 chars long");
    }

    @Provide
    Arbitrary<String> vehicleEquipmentsWithInvalidLength() {
        return Arbitraries.strings()
                .alpha()
                .unique()
                .filter(equipment -> equipment.length() != 3)
                .map(String::toUpperCase);
    }

    @Property
    void should_throw_exception_when_vehicle_equipment_contains_non_alphabetical_chars(
            @ForAll("vehicleEquipmentsWithNonAlphabeticalCharacters") final String equipment
    ) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleEquipment.of(equipment))
                .withMessageContaining("Equipment is invalid: non-alphabetical chars");
    }

    @Provide
    Arbitrary<String> vehicleEquipmentsWithNonAlphabeticalCharacters() {
        return Arbitraries.strings()
                .ascii()
                .ofLength(3)
                .unique()
                .filter(equipment -> !equipment.matches("[a-zA-Z]{3}"))
                .map(String::toUpperCase);
    }
}
