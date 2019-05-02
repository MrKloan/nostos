package io.fries.nostos.core.vehicle

internal class VehicleNumber(number: String) {

    companion object {
        private const val VEHICLE_NUMBER_LENGTH = 6
    }

    val number = number.toUpperCase()

    init {
        require(number.length == VEHICLE_NUMBER_LENGTH) { "Vehicle number must have a length of $VEHICLE_NUMBER_LENGTH" }
    }
}