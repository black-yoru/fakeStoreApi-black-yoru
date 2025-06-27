package org.com.utility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilitiesRegression {

    private RequestSpecification requestSpecification;

    public UtilitiesRegression(RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    }

    public Response deleteBulk(int [] id){
        return requestSpecification
                .when()
                .delete("/products/" + id);
    }
}
