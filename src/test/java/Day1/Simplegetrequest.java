package Day1;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Simplegetrequest {

String url="http://3.94.92.170:8000/api/spartans";

@Test
    public void test1(){

    Response response =RestAssured.get(url);//send a get request and save response in the Response object
    System.out.println(response.statusCode());// print response status code
//print response body
    response.prettyPrint();



}
}
