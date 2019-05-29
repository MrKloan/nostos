package io.fries.nostos.api

import org.eclipse.microprofile.config.inject.ConfigProperty
import java.lang.String.format
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Greeter(@ConfigProperty(name = "greetings.message") val message: String) {
    fun greet(name: String): String = format(message, name)
}