package Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HRrequests {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://3.94.92.170:8000/ords/hr";
    }
    @DisplayName("Get request to /regions")
    @Test
    public void test(){

        Response response=RestAssured.get("/regions");
        System.out.println(response.statusCode());
    }
    /* Given accept type is application/json
    When user sends get request to /regions/2
    Then response status code must be 200
    and content type equals to application/json
    and response body contains Americas
     */
    @DisplayName("Get request to /regions/2")
    @Test
    public void test1(){

        Response response=RestAssured.get("/regions/2");
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        response.prettyPrint();
        Assertions.assertEquals(response.body().asString().contains("Americas"),true);
    }


}
