//package org.com.utility.report;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import java.util.Properties;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.requestSpecification;
//
//public class QaseApiCornerTest {
//
//    private String URL;
//    private int idRun;
//    private RequestSpecification requestSpecification;
//
//
//
//    public int testRunCorner() throws IOException {
//
//        Properties props = new Properties();
//        props.load(new FileInputStream("src/main/resources/option.properties"));
//
//        this.URL = props.getProperty("qase");
//
//        this.requestSpecification = RestAssured.given().baseUri(URL);
//
//
//        String requestBody =
//                "{\n" +
//                "  \"title\": \"Corner Test\",\n" +
//                "  \"case\": [7, 8, 9, 10]\n" +
//                "}";
//
//
//        Response response =
//                given()
//                        .header("token", "f1366a9d29db7a89d1c124a89306fdaae8e76d491ace3e8e1e8577ab07ba4ba3")
//                        .header("Accept", "application/json")
//                        .header("Content-Type", "application/json")
//                        .body(requestBody)
//                        .when()
//                        .post("/v1/run/TFA")
//                        .then()
//                        .extract().response();
//
//
//        System.out.println("FULL RESPONSE: " + response.getBody().asPrettyString());
//        idRun = response.jsonPath().getInt("result.id");
//
//
//        if (idRun == 0) {
//            throw new RuntimeException("ID not found");
//        }
//
//
//        System.out.println("ID Test Run: " + idRun);
//        return idRun;
//    }
//
//
//
//
//    public void updateClass(int testRunId, List<Integer> caseId, String status) {
//        StringBuilder results = new StringBuilder("[");
//        for (int i = 0; i < caseId.size(); i++) {
//            results.append("{")
//                    .append("\"case_id\": ").append(caseId.get(i)).append(", ")
//                    .append("\"status\": \"").append(status).append("\"")
//                    .append("\"comment\": \"Executed via Automation\", ")
//                    .append("\"time\": 10")
//                    .append("}");
//            if (i < caseId.size() - 1) {
//                results.append(", ");
//            }
//        }
//        results.append("]");
//
//        String requestBody = "{ \"results\": " + results.toString() + " }";
//
//        System.out.println("Request body:\n" + requestBody);
//
//        requestSpecification = RestAssured.given().baseUri(URL);
//        Response response = given()
//                .header("token", "5f7d95bebe0ad046477be0a27ccdd52059fd8623375769c8ad2011057687eebc")
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .body(requestBody)
//                .when()
//                .post("/v1/result/TFA/" + testRunId + "/bulk")
//                .then()
//                .extract()
//                .response();
//
//        System.out.println("full response:\n " + response.getBody().asString());
//
//
//    }
//}
