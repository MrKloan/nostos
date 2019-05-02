package io.fries.nostos.core.vehicle

internal class VehicleNumber(number: String) {

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 6
        private const val ZERO = '0'

        private val validNumberPattern = Regex("[a-zA-Z0-9]{$MIN_LENGTH,$MAX_LENGTH}")
    }

    val number = number.toUpperCase().padStart(MAX_LENGTH, ZERO)

    init {
        require(validNumberPattern.matches(number)) { "Vehicle number '$number' should be an alphanumerical string between $MIN_LENGTH and $MAX_LENGTH characters" }
    }
}