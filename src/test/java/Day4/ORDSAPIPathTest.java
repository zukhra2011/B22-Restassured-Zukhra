package Day4;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSAPIPathTest {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.94.92.170:8000";
    }
    @DisplayName("Get request to countries with Path method")
    @Test
    public void test1(){
Response response= given().accept(ContentType.JSON).queryParam("q","{\"region_id\": 2}")
        .when().get("/countries");
assertEquals(200, response.statusCode());
//print limit result
    // System.out.println("response.path(\"limit\")= "+response.path("limit"));
   //  System.out.println("response.path(\"hasMore\")="+ response.path("hasMore"));
     //print first CountryID
        String firstCountryId=response.path("items[0].country_id");
        System.out.println("FirstCountryId= "+firstCountryId);
        //print second country name
        String secondcountryname=response.path("items[1].country_name");
        System.out.println("secondcountryname= "+ secondcountryname); //Brazil


       //print  {"http://3.94.92.170:1000/ords/hr/countries/CA"} thirdHref
        String linkname= response.path("items[2].links[0].href");
        System.out.println("thirdHref= "+ linkname);

        //get me all the country names
        List<String> countrynames=response.path("items.country_name");
        System.out.println("countryNames="+countrynames);
        //assert that all regions ids are equals to 2
        List<Integer> allRegionIds=response.path("items.region_id");
        for(Integer regionsID:allRegionIds){
            System.out.println("regionsID= "+ regionsID);
            assertEquals(2,regionsID);


        }
 }
}
