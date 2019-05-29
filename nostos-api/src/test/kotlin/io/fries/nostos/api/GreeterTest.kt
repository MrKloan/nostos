package io.fries.nostos.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GreeterTest {

    @Test
    internal fun should_greet() {
        val greeter = Greeter("Hello, %s!")

        val greetings = greeter.greet("World")

        assertThat(greetings).isEqualTo("Hello, World!")
    }
}