package io.fries.nostos.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class GreetingsResourceTest {

    private val greeter = mock(Greeter::class.java)
    private val greetingResource = GreetingsResource(greeter)

    @Test
    internal fun should_greet() {
        val name = "World"
        val message = "GREETER_MESSAGE"
        given(greeter.greet(name)).willReturn(message)

        val greetings = greetingResource.hello(name)

        verify(greeter).greet(name)
        assertThat(greetings).isEqualTo("{\"message\":\"$message\"}")
    }
}