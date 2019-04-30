package io.fries.nostos.core.coordinates

class Coordinates private constructor(latitude: Latitude, longitude: Longitude) {

    val latitude = latitude.degrees
    val longitude = longitude.degrees

    companion object {
        fun of(latitude: Double, longitude: Double): Coordinates {
            return Coordinates(Latitude(latitude), Longitude(longitude))
        }
    }
}