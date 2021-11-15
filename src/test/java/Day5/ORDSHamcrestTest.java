package Day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class ORDSHamcrestTest extends HRTestBase {
    @DisplayName("Get request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){
        //send a get request to endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_Prog
        //verify first name are....(find proper method to check against list)
        //verify emails without checking order (provide emails in different order,
        //just make sure it has same emails)
        given().accept(ContentType.JSON).and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees").then().statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce","David", "Valli", "Diana"));

    }
    //to get names directly,Alexander, Bruce, David
}
