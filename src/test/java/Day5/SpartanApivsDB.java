package Day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.DBUtils;
import utilities.SpartanTestBase;

import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanApivsDB extends SpartanTestBase {
    @DisplayName("Get one spartan from api and database")
    @Test
    public void testDB1(){
        //get id, name, gender, phone from database
        //get some information from api
        //compare
        String query="select spartan_id, name,gender,phone from spartans\n" +
                "where spartan_id=15";
        Map<String,Object> dbMap= DBUtils.getRowMap(query);
        System.out.println(dbMap);

        Map<String, Object> apiMap= given().accept(ContentType.JSON).pathParam("id",15)
                .when().get("/api/spartans/{id}").then().statusCode(200).and()
                .contentType("application/json").extract().response().as(Map.class);
        System.out.println(apiMap);
        //compare api vs database
        assertThat(apiMap.get("id"),is(dbMap.get("Spartan_ID")));
        assertThat(apiMap.get("name"),is(dbMap.get("name")));
        assertThat(apiMap.get("gender"),is(dbMap.get("gender")));
        assertThat(apiMap.get("phone"),is(dbMap.get("phone")));


    }




    @AfterAll
    public static void teardown(){
        DBUtils.destroy();
    }
}
