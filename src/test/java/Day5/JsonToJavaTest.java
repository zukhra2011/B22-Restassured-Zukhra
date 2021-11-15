package Day5;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonToJavaTest extends SpartanTestBase {
    @DisplayName("Get one Spartan and deserialize to map")
    @Test
    public void test1(){
        Response response=given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        Map <String,Object> jsonMap= response.as(Map.class);
        System.out.println(jsonMap.toString());
        //deserialization converting from jason to java

    }
    @DisplayName("Get all spartans to Java data Structure")
    @Test
    public void getAllSpartan(){
     Response response= get("/api/spartans").then().statusCode(200)
                .extract().response();
     //we need to convert to json to java which is deserialize
        List<Map<String,Object>> jsonList=response.as(List.class);
        System.out.println("jsonList.get(1).get(\"name\")="+jsonList
                .get(1).get("name"));
        Map<String, Object> spartan3=jsonList.get(3);
        System.out.println(spartan3);
    }

}
