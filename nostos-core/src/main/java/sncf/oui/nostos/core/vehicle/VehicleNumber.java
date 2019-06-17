package sncf.oui.nostos.core.vehicle;

import org.immutables.value.Value;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Value.Immutable
abstract class VehicleNumber {
    private static final int NUMBER_LENGTH = 6;
    private static final String ZERO_PADDING = "0";

    public static VehicleNumber of(final String number) {
        return ImmutableVehicleNumber.of(number);
    }

    @Value.Parameter
    abstract String number();

    @Value.Check
    VehicleNumber normalize() {
        return isValid()
                ? this
                : of(normalizedNumber());
    }

    private boolean isValid() {
        if (number().isBlank()) {
            throw new IllegalArgumentException("Vehicle number should not be empty");
        }
        if (number().length() > NUMBER_LENGTH) {
            throw new IllegalArgumentException("Vehicle number should have 6 chars or less");
        }
        if (!number().matches("[a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("Vehicle number should not contain special character");
        }

        return number().equals(number().toUpperCase()) // NOSONAR
                && number().length() == NUMBER_LENGTH;
    }

    private String normalizedNumber() {
        return leftPad(number(), NUMBER_LENGTH, ZERO_PADDING).toUpperCase();
    }

}
