package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.utility.Utilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FunctionMassProduct {

    private String url;

    Utilities helper;
    RequestSpecification requestSpecification;



    @BeforeMethod
    void setUp() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/option.properties"));

        url = props.getProperty("url");
        requestSpecification = RestAssured.given().baseUri(url);

        helper = new Utilities(requestSpecification);
    }

    @Test
    void createCustomProducts(){
        Map<String, Object> createProduct = new HashMap<>();
        createProduct.put("title", "ventela");
        createProduct.put("description", "sepatu vulcan terjangkau");
        createProduct.put("price", "300000");

        Response response = helper.postProduct(createProduct);
        System.out.println(response);

        response.then()
                .body("title", org.hamcrest.Matchers.equalTo("ventela"))
                .body("description", org.hamcrest.Matchers.equalTo("sepatu vulcan terjangkau"))
                .body("price", org.hamcrest.Matchers.equalTo("300000"));
    }
}
