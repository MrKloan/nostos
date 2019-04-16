package oui.sncf.io.coordinates;

class Longitude {

    public static final int MAX_DEGREES = 180;
    public static final int MIN_DEGREES = -180;

    private final double degrees;

    private Longitude(final double degrees) {
        this.degrees = degrees;
    }

    public static Longitude of(final double degrees) {
        if (degrees <= MIN_DEGREES || degrees >= MAX_DEGREES) {
            throw new IllegalArgumentException("longitude should be between -180° and 180°");
        }

        return new Longitude(degrees);
    }

    public double getDegrees() {
        return degrees;
    }
}
