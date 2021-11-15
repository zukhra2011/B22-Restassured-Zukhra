package Day6;

import Pojo.Spartan;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {


    @DisplayName("Get one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){
Response response= given().accept(ContentType.JSON).and().pathParam("id", 15)
        .when().get("/api/spartans/{id}").then()
        .statusCode(200).extract().response();
//de serialize --> Json to POJO  (java custom class)
// 2 different way to do this
SOAP
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getID()="+spartan15.getId());
        System.out.println("spartan15.getGender()="+spartan15.getGender());
        //second way of deserialize json to java
        //using JsonPath to deserialize to custom class
             JsonPath jsonPath= response.jsonPath();
             Spartan s15=jsonPath.getObject("",Spartan.class);
             System.out.println(s15);
             System.out.println("s15.getName()="+s15.getName());
             System.out.println("s15.getPhone() = " + s15.getPhone());

    }
    @DisplayName("Get request")
    @Test
    public void spartanSearchWithPojo(){
       JsonPath jsonPath= given().accept(ContentType.JSON).and()
               .queryParams("nameContains","a",
                "gender","Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();
             Spartan s1=jsonPath.getObject("content[0]",Spartan.class);
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender = " + s1.getGender());
    }
}
