package Day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {
@DisplayName("Get spartan/search and chaining together")
@Test
    public void test1(){
    //along with this statement, i want to save name inside the List String
   List<String> names= given().accept(ContentType.JSON).and()
            .queryParams("nameContains", "j",
                    "gender","Male")
            .when().get("/api/spartans/search").then()
            .statusCode(200).and().body("totalElement", greaterThanOrEqualTo(3))
            .extract().response().jsonPath().getList("content.name");
            System.out.println(names);

}
    @DisplayName("Get spartan/search and chaining together")
    @Test
    public void test2(){
    //saving the status code.

        int statusCode= given().accept(ContentType.JSON).and()
                .queryParams("nameContains", "j",
                        "gender","Male")
                .when().get("/api/spartans/search").then()
                .statusCode(200).and().body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().statusCode();
        System.out.println(statusCode);

}}
