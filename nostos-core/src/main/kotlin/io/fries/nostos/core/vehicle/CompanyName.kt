package io.fries.nostos.core.vehicle

class CompanyName(name: String) {

    init {
        require(name.isNotEmpty()) { "Company name cannot be empty" }
    }
}
