package Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Spartantestwithparameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.94.92.170:8000";
}
/*Given accept type is Json
And id parameter value is 5
When use sends get request to /api/spartans/{id}
Then response status code should be 200
And response content type: application/json
And "Blythe" should be response payload or body (same thing)
*/

@DisplayName("Getting request to /api/spartans/{id} wiht ID 5") //postman is the manual. here is the automation.
@Test
public void test1() {
    // passing path parameter.
    Response response = given().accept(ContentType.JSON).and().pathParam("id", 5).
            when().get("api/spartans/{id}");
    assertEquals(200, response.statusCode());
    assertEquals("application/json", response.contentType());
    //verify Blythe in the json payload/body
    assertTrue(response.body().asString().contains("Blythe"));
}
/*Given accept type is Json
And id parameter value is 500
When use sends get request to /api/spartans/{id}
Then response status code should be 404
And response content type: application/json
And "Not Found" should be response payload or body (same thing)
*/
    @DisplayName("Get request to /api/spartans/{id}")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON).and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }
    /*Given accept type is Json
And query parameter value are:
gender|female
nameContains|e
When use sends get request to /api/spartans/search
Then response status code should be 200
And response content type: application/json
And "Female" should be response payload or body (same thing)
And "Janette" should be response payload or body (same thing)
*/
    @DisplayName("Get request to api/spartans/search with Query params")
    @Test
    public void test3(){
        Response response= given().log().all()//show us what we are sending below
                .accept(ContentType.JSON)
                .and().queryParam("nameContains","e")
                .and().queryParam("gender", "Female")
                .when().get("/api/spartans/search");
        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }
    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        //create a map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().
                log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

}}