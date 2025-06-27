package org.com.utility;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilitiesNegative {

    private RequestSpecification requestSpecification;


    public UtilitiesNegative(RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    }



    public Response getNonId(int id){
        return requestSpecification
                .when()
                .get("/products/" + id);
    }



    public Response getCharId(String id){
        return requestSpecification
                .when()
                .get("/products/" + id);
    }



    public Response getFloatId(float id){
        return requestSpecification
                .when()
                .get("/products");
    }

}
