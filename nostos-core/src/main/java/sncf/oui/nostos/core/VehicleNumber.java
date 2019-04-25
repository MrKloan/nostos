package sncf.oui.nostos.core;

import static org.apache.commons.lang3.StringUtils.leftPad;

public class VehicleNumber {
    public static final int NUMBER_LENGTH = 6;
    public static final String ZERO_PADDING = "0";
    private final String number;

    private VehicleNumber(final String number) {
        this.number = number;
    }

    public static VehicleNumber of(final String number) {
        if (number.length() > NUMBER_LENGTH) {
            throw new IllegalArgumentException("Vehicle number should have 6 chars or less");
        }
        if (!number.matches("[a-zA-Z0-9]*")) {
            throw new IllegalArgumentException("Vehicle number should not contain special character");
        }
        final String formattedNumber = leftPad(number, NUMBER_LENGTH, ZERO_PADDING);
        return new VehicleNumber(formattedNumber.toUpperCase());
    }

    public String getNumber() {
        return number;
    }
}
