package sncf.oui.nostos.core.vehicle;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

class VehicleEquipmentTest {

    Arbitrary<String> validVehicleEquipments() {
        return Arbitraries.strings()
                .alpha()
                .ofLength(3)
                .unique()
                .map(String::toUpperCase);
    }
}
