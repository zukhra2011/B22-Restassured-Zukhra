package Day2;

import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeTest {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.94.92.170:8000";
    }
    /* Given Accept type application/xml
    When user send Get request to api/spartans end point
    Then status code must be 406
    And response Content Type must be application/xml
     */
        @DisplayName("getting api/spartans/10")
                @Test
                public void test1(){
                Response response=given().accept(ContentType.XML).when().get("/api/spartans/10");

                assertEquals(406, response.statusCode()) ;

                assertEquals("application/xml;charset=UTF-8", response.contentType());


}

}