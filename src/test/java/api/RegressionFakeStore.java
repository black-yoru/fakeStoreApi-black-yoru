package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.utility.Utilities;
import org.com.utility.UtilitiesRegression;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RegressionFakeStore {

    private String URL;
    private int id;

    private RequestSpecification requestSpecification;
    private Utilities helper;
    private UtilitiesRegression helperRegression;



    @BeforeMethod
    void setUp() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/option.properties"));

        URL = props.getProperty("url");

        requestSpecification = RestAssured.given().baseUri(URL);

        helper = new Utilities(requestSpecification);
        helperRegression = new UtilitiesRegression(requestSpecification);

    }



    @org.testng.annotations.Test
    void postBulkProducts(){
        int statusCode = 404;

        for (int i = 21; i <=10; i++) {
        Map<String, Object> addProduct = new HashMap<>();
        addProduct.put("id", i + id);
        addProduct.put("title", "rokok djarum");
        addProduct.put("price", (i * 100) + 23000);
        addProduct.put("description", "rokok membunuhmu");

        Response response = helper.postProduct(addProduct);

        response.then()
                .statusCode(404)
                .body("id", org.hamcrest.Matchers.equalTo(id))
                .body("title", org.hamcrest.Matchers.equalTo("rokok djarum"))
                .body("price", org.hamcrest.Matchers.equalTo(23000))
                .body("description", org.hamcrest.Matchers.equalTo("rokok membunuhmu"));

            System.out.println("Response API: " + response.statusCode() + " " + response.getStatusCode());
            helper.validateResponse(response, statusCode);
        }
    }




    @org.testng.annotations.Test
    void bulkCreateData(){

        for (int i = 0; i <= 5; i++){
            Map<String, Object> updateProduct = new HashMap<>();
            updateProduct.put("id", i + id);
            updateProduct.put("title", "rokok djarum");
            updateProduct.put("price", (i * 100) + 23000);

            Response response = helper.updateProduct(i, updateProduct);
            System.out.println(response.asPrettyString());

            System.out.println(response.statusCode());
            System.out.println("Response API: " + response.getStatusCode());

        }
    }

    @Test
    void deleteBulk(){
        int[] id = {0,1,2,3,4,5};
        Response response = helperRegression.deleteBulk(id);
        System.out.println(response.statusCode());
    }
}
