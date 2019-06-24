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
    void should_throw_exception_when_vehicle_equipment_length_is_invalid(@ForAll("vehicleEquipmentsWithInvalidLength") String vehicleEquipmentLengthInvalid) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> VehicleEquipment.of(vehicleEquipmentLengthInvalid))
                .withMessage("Vehicle Equipment length should be 3 chars.");
    }

    @Provide
    Arbitrary<String> vehicleEquipmentsWithInvalidLength() {
        return Arbitraries.strings()
                .alpha()
                .unique()
                .filter(equipment -> equipment.length() != 3)
                .map(String::toUpperCase);
    }
}
