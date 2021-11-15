package Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Spartan {
    String baseUrl="http://3.94.92.170:8000";

//Give accept type application/json
//when user send get request to api/spartans end point
//then status code must be 200
//then response type content must be application json.
//and response body should include spartan result
/*@Test
public void test1(){
    Response response=RestAssured.given().accept(ContentType.JSON).when()
            .get(baseUrl+"/api/spartans");
    //printing status code from response object
    System.out.println("response.statusCode()=" + response.statusCode());
    //printing response content type from response object
    System.out.println("response.ContenType()="+ response.contentType());
    response.prettyPrint();
//how to API test then?
// verifying status code is 200.
    Assertions.assertEquals(response.statusCode(),200);
    // verifying the content type is application/json
    Assertions.assertEquals(response.contentType(),"application/json");*/
// Give accept type application/json
//when user send get request to api/spartans end point
//then status code must be 200
//then response type content must be application json.
//json body should contain Fidole.
    @DisplayName("Get one spartan/api/spartans/3 and verify")
            @Test
            public void test2() {
        Response response1 = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");// 3 is Fidole.
        System.out.println("response.statusCode()=" + response1.statusCode());
        System.out.println("response.contentType()=" + response1.contentType());
        Assertions.assertEquals(response1.statusCode(), 200);//actual result.
        Assertions.assertEquals(response1.contentType(), "application/json");
//verifying json body contains Fidole
        Assertions.assertTrue(response1.body().asString().contains("Fidole"));

    }
//Given no headers provided.
// When users sends Get request to/api/hello
// then response status code should be 200
// and content type header should be "text/plain;charset=UTF-8"
// and header should contain date
// content-length should be 17
// body should be "hell from Sparta"
@DisplayName("Get request to /api/hello")
@Test
public void test3(){
Response response=RestAssured.get(baseUrl+"/api/hello");
//verify status code 200
    Assertions.assertEquals(200, response.statusCode());
    Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());
    Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
  //how to get any header from response using header key?
    System.out.println("response.header(\"Content-Length\")="+ response.header("Content-length"));
    System.out.println(response.header("Date"));
    //Verifying content length is 17.
    Assertions.assertEquals("17",response.header("Content-Length"));
    Assertions.assertEquals("Hello from Sparta",response.body().asString());



        }





}