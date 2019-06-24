package sncf.oui.nostos.core.vehicle;

import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
public abstract class VehicleEquipment {

    public static VehicleEquipment of(final String vehicleEquipment) {
        return ImmutableVehicleEquipment.of(vehicleEquipment);
    }

    @Value.Check
    void check() {
        if (equipment().length() != 3) {
            throw new IllegalArgumentException("Vehicle Equipment length should be 3 chars.");
        }
    }

    @Parameter
    abstract String equipment();
}
