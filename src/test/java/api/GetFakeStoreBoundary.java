package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.utility.Utilities;

import org.com.utility.UtilitiesNegative;
//import org.com.utility.report.QaseApiCornerTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class GetFakeStoreBoundary {

    private String URL;
    private int ID;

    private RequestSpecification requestSpecification;
    private Utilities helper;
    private UtilitiesNegative helperNegative;

//    private QaseApiCornerTest qaseApiCornerTest;



//    @BeforeClass
//    void hooks() throws IOException {
//        qaseApiCornerTest = new QaseApiCornerTest();
//        System.out.println("Before Test: ");
//        ID = qaseApiCornerTest.testRunCorner();
//
//    }


    @BeforeMethod
    void setUp() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/option.properties"));

        URL = properties.getProperty("url");

        requestSpecification = RestAssured.given().baseUri(URL);

        helper = new Utilities(requestSpecification);
        helperNegative = new UtilitiesNegative(requestSpecification);

    }



    @org.testng.annotations.Test
    void getProductsNegative(){
        Response response = helper.getLinkApi();
        System.out.println(response.asPrettyString());
    }




    @org.testng.annotations.Test
    void getIdNegative(){
        int id = 21;
        int statusResponse = 400;
        Response response = helperNegative.getNonId(id);
        System.out.println(response.asPrettyString());
        System.out.println("Response API: " + response.statusCode());
        helper.validateResponse(response, statusResponse);
    }


    @org.testng.annotations.Test
    void getIdCharNegative(){
        String id = "a";
        int statusCode = 400;
        Response response = helperNegative.getCharId(id);
        System.out.println("Response API: " + response.getStatusCode());
        helper.validateResponse(response, statusCode);
    }


    @Test
    void getIdFloat(){
        float id = 1;
        int statusCode = 400;
        Response response = helperNegative.getFloatId(id);
        System.out.println("Response actual API: " + response.statusCode() + "\nexpected response: " + statusCode);

    }

//    @AfterClass
//    void tearDown(){
//        List<Integer> Id = Arrays.asList(1, 2, 3, 4, 5, 6);
//        String status = "passed";
//        qaseApiCornerTest.updateClass(ID, Id, status);
//        System.out.println("===After - Finish===");
//
//    }
}

