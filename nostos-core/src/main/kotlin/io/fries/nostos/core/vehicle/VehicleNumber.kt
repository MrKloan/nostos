package io.fries.nostos.core.vehicle

internal class VehicleNumber(number: String) {

    companion object {
        private const val VEHICLE_NUMBER_LENGTH = 6
        private const val ZERO = '0'
    }

    val number = number.toUpperCase().padStart(VEHICLE_NUMBER_LENGTH, ZERO)

    init {
        require(number.length <= VEHICLE_NUMBER_LENGTH) { "Vehicle number cannot be longer than $VEHICLE_NUMBER_LENGTH" }
    }
}