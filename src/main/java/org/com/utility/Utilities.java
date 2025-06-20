package org.com.utility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

}
