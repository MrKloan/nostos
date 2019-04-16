package oui.sncf.io.coordinates;

class Latitude {

    private final double degrees;

    private Latitude(final double degrees) {
        this.degrees = degrees;
    }

    public static Latitude of(final double degrees) {
        if (degrees <= -90 || degrees >= 90) {
            throw new IllegalArgumentException("latitude should be between -90° and 90°");
        }

        return new Latitude(degrees);
    }

    public double getDegrees() {
        return degrees;
    }
}
