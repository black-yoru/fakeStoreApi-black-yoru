package org.com.utility.report;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class QaseApiSmokeTest {

    private String URL;
    private Integer idRun;

    private RequestSpecification requestSpecification;



/*  0. membuat method yang mengembalikan integer konteksnya disini adalah ID qase test*/
    public int testRun() throws IOException {

/*  1. membuat objek untuk memanggil link Qase dengan class library properties*/
    Properties props = new Properties();
    props.load(new FileInputStream("src/main/resources/option.properties"));


/*  2. attribute URL dimasukan objek link qase io*/
    URL = props.getProperty("qase");


/*  3.  interface restAssured spesifik agar tidak tumpang tindih masukan objek interface RestAssured dan syarat persiapan
    untuk membuka link qase io */
    requestSpecification = RestAssured.given().baseUri(URL);


/*  4. attribute String berikan sturktur objek Json untuk diverifikasi oleh API*/
    String requestBody =
            "{\n" +
                    "  \"title\": \"Smoke Test\",\n" +
                    "  \"case\": [1, 2, 3, 4, 5, 6]\n" +
                    "}";


    Response response =
    given()
            .header("token", "f1366a9d29db7a89d1c124a89306fdaae8e76d491ace3e8e1e8577ab07ba4ba3")
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post("/v1/run/TFA")
            .then()
            .extract().response();



    System.out.println("full response:\n " + response.getBody().asPrettyString());
    idRun = response.jsonPath().getInt("result.id");

    if (idRun == 0) {
        throw new RuntimeException("failed get test run ID");
    }

    System.out.println("id test run: " + idRun);
    return idRun;

    }






    public void updateClass(int testRunId, List<Integer> caseId, String status){
        StringBuilder results = new StringBuilder("[");
        for (int i = 0; i < caseId.size(); i++) {
            results.append("{")
                    .append("\"case_id\": ").append(caseId.get(i)).append(", ")
                    .append("\"status\": \"").append(status).append("\"")
                    .append("}");
            if (i < caseId.size() - 1) {
                results.append(", ");
            }
        }
        results.append("]");

        String requestBody = "{ \"results\": " + results.toString() + " }";

        System.out.println("Request body:\n" + requestBody);

        requestSpecification = RestAssured.given().baseUri(URL);
        Response response = given()
                .header("token", "5f7d95bebe0ad046477be0a27ccdd52059fd8623375769c8ad2011057687eebc")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/v1/result/TFA/" + testRunId + "/bulk")
                .then()
                .extract()
                .response();

        System.out.println("full response:\n " + response.getBody().asString());

    }
}
