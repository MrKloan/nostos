package io.fries.nostos.api

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
internal class GreetingsResourceIT {

    @Test
    internal fun should_greet() {
        given()
                .`when`().get("/hello/World")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Hello, World!"))
    }
}