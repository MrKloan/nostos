package io.fries.nostos.api

import java.lang.String.format
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/hello")
class GreetingsResource(val greeter: Greeter) {

    @GET
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    fun hello(@PathParam("name") name: String): String = format("{\"message\":\"%s\"}", greeter.greet(name))
}