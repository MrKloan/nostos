package sncf.oui.nostos.core.vehicle;

import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.util.HashSet;

@Immutable
public abstract class VehicleEquipment {

    public static VehicleEquipment of(final String vehicleEquipment) {
        return ImmutableVehicleEquipment.of(vehicleEquipment);
    }

    @Value.Check
    void check() {
        var messages = new HashSet<String>();
        if (equipment().length() != 3) {
            messages.add("not 3 chars long");

        }

        if (!equipment().matches("[A-Z]*")) {
            messages.add("non-alphabetical chars");
        }

        if (!messages.isEmpty()) {
            throw new IllegalArgumentException(
                    "Equipment is invalid: " + String.join(", ", messages)
            );

        }
    }

    @Parameter
    abstract String equipment();
}
