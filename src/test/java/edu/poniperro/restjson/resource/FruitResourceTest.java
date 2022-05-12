package edu.poniperro.restjson.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
class FruitResourceTest {

    @Test
    void getTest() {
        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name", containsInAnyOrder("Apple", "Pineapple"),
                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit")
                );
    }

    @Test
    void postDeleteTest() {
        given()
                .body("{\"name\": \"Banana\", \"description\": \"Brings a Gorilla too\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "name", containsInAnyOrder("Apple", "Pineapple", "Banana"),
                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit", "Brings a Gorilla too")
                );

        given()
                .body("{\"name\": \"Banana\", \"description\": \"Brings a Gorilla too\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name", containsInAnyOrder("Apple", "Pineapple"),
                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit")
                );
    }
}