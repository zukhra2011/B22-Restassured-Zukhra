package Day3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDS_APIwithparameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.94.92.170:8000";
    }
/* Given accept type is json
And parameters: q={"region_id:2"}
When users sends a GET request to "/countries"
Then status code is 200
And content type is application/json
And payload should contain "United States of America
 */
    @DisplayName("Get request to countries with param q={region_id:2")
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON).and()
                .queryParam("q","{\"region_id\":2}")
                .log().all().when().get("/countries");
        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));
        response.prettyPrint();
    }
//Send a get request to employees and get only employees who works as a IT_PROG
@DisplayName("Get request to /employees with param q={")
@Test
public void test2(){
    Response response=given().accept(ContentType.JSON).and()
            .queryParam("q","{\"job_id\":\"IT_PROG\"}")
            .log().all().when().get("/employees");
    assertEquals(200, response.statusCode());
    assertEquals("application/json",response.contentType());
    assertTrue(response.body().asString().contains("IT_PROG"));
    response.prettyPrint();


}}
