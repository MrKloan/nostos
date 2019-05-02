package io.fries.nostos.core.vehicle

internal class CompanyName(name: String) {

    val name = name.trim()

    init {
        require(name.isNotEmpty()) { "Company name cannot be empty" }
    }
}
