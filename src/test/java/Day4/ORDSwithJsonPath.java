package Day4;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSwithJsonPath {
    @BeforeAll
    public static void init()
    {baseURI = "http://3.94.92.170:1000/ords/hr";}
    @DisplayName("Get request to Countries.")
    @Test
    public void test1(){
        Response response = get("/countries");
        //get the second country with json path.
        JsonPath jsonPath=response.jsonPath();

        String secondCountryName= jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName="+secondCountryName);
        //get all country ids
        //items.country_id
        List<String> allCountryIds=jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);
        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
        System.out.println(countryNameWithRegionId2);

    }
    @DisplayName("Get request to/employees with query param")
    @Test
    public void test2(){
      Response response=  given().queryParam("limit",107).when().get("/employees");

        //get me all email of employees who is working as IT Prog.
        JsonPath jsonPath=response.jsonPath();
        List<String> employeeITProgs=jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);
        //get me first name of employees who is making more than 1000
      List<String> empNames=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(empNames);
//get the max salary first name
        String kingFirstName=jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingFirstName);
    }

}
