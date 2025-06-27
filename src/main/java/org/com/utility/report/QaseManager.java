//package org.com.utility.report;
//
//import io.restassured.specification.RequestSpecification;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//import static io.restassured.RestAssured.given;
//
//public class QaseManager {
//
//    private String URL;
//    private String token;
//    private Integer idRun;
//
//    private RequestSpecification requestSpecification;
//
//
//    private final java.util.Map<String, Integer> testRunIds = new java.util.HashMap<>();
//
//
//    /*  0. membuat method yang mengembalikan integer konteksnya disini adalah ID qase test*/
//    public void QaseApi() throws IOException {
//
//        /*  1. membuat objek untuk memanggil link Qase dengan class library properties*/
//        Properties props = new Properties();
//        props.load(new FileInputStream("src/main/resources/option.properties"));
//
//
//        /*  2. attribute URL dimasukan objek link qase io*/
//        this.URL = props.getProperty("qase");
//        this.token = props.getProperty("token");
//
//
///*  3.  interface restAssured spesifik agar tidak tumpang tindih masukan objek interface RestAssured dan syarat persiapan
//    untuk membuka link qase io */
//        this.requestSpecification = given()
//                .baseUri(URL)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .header("token", token);
//
//    }
//
//
//
//}