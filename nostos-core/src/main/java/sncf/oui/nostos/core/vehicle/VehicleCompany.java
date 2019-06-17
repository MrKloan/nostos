package sncf.oui.nostos.core.vehicle;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
public abstract class VehicleCompany {

    public static VehicleCompany of(final String company) {
        return ImmutableVehicleCompany.of(company);
    }

    @Parameter
    abstract String company();

    @Check
    VehicleCompany check() {
        return isValid()
                ? this
                : of(normalizedCompany());

    }

    private boolean isValid() {
        if (company().isBlank()) {
            throw new IllegalArgumentException("Company should not be empty");
        }

        return company().equals(normalizedCompany());
    }

    private String normalizedCompany() {
        return company().trim();
    }
}
