package org.com.utility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Utilities {


    private final RequestSpecification requestSpecification;

    public Utilities(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }


    public Response getLinkApi(){
        return requestSpecification
                .when()
                .get("/products");
    }



    public Response getSingleProduct(int id){
        return requestSpecification
                .when()
                .get("/products/" + id);
    }


/*  method response diberikan parameter HashMap tujuannya method ini memberikan respon dengan struktur API
*   dengan format json*/
    public Response postProduct(Map<String, Object> payload){
        return requestSpecification
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/products");
    }


    public Response updateProduct(int id, Map<String, Object> payload){
        return requestSpecification
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put("/products/" + id);
    }



    public void validateResponse(Response response, int expectedStatusCode){
        response.then()
                .statusCode(expectedStatusCode);
//  method ini hanya fungsi tidak mengembalikan nilai atau hanya sebagai fungsi saja
    }


    public Response delete( int id){
        return requestSpecification
                .when()
                .delete("/products/" + id);
    }


}
