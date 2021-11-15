package Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class Spartantestswithparam {
    @BeforeAll
    public static void init() {
            baseURI = "http://3.94.92.170:8000";
        }
        /*Given accept type is json
        And path param id is 10
        When user a get request to "/api/spartans/{id}"
        Then status code is 200
        And content type is "application/jason"
        And response payload values match the following:
        id is 10, name is "Lorenza", gender is "Female", phone is 3312820936
         */
        @DisplayName("Get one Spartan with Path method ")
        @Test
        public void test1(){ //First do it manually, through Postman.
       Response response= given().accept(ContentType.JSON)
               .and().pathParam("id",10)
               .when().get("/api/spartans/{id}");
assertEquals(200, response.statusCode());
assertEquals("application/json", response.contentType());
            /*System.out.println(response.path("id").toString());
            System.out.println(response.path("name").toString());
            System.out.println(response.path("gender").toString());
            System.out.println(response.path("phone").toString()); */

            int id= response.path("id");
            String name= response.path("name");
            String gender= response.path("gender");
            long phone= response.path("phone");
            System.out.println("id= "+id);
            System.out.println("name= "+name);
            System.out.println("gender= "+gender);
            System.out.println("phone= "+phone);
            
            //assert the values
            assertEquals(10,id);
            assertEquals("Lorenza",name);
            assertEquals("Female",gender);
            assertEquals(3312820936l,phone);

}
   @DisplayName("Get all Spartan and navigate Path () ")
    @Test
    public void test2(){
       Response response= given().accept(ContentType.JSON)
               .when().get("/api/spartans");
       int firstId=response.path("id[0]");
       System.out.println(firstId);
       String name= response.path("name[0]");
       System.out.println(name);
       String lastfirstname=response.path("name[-1]");
       System.out.println(lastfirstname);
       List<String> names=response.path("name");
       System.out.println(names);
       //print each name one by one.
       for (String n:names){
           System.out.println(n);
       }


}}
