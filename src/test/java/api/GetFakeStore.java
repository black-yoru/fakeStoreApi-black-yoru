package api;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.utility.report.QaseApiSmokeTest;
import org.com.utility.Utilities;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GetFakeStore {

    private String URL;
    private int id;
    private int[] ids;

    private int ID;

//request untuk konfigurasi sebelum permintaan dikirim dan untuk test lain menggunakan API yang sama
//tanpa perlu membuat class RestAssured berulang. cukup satu kali
    private RequestSpecification requestSpecification;

    private Utilities helper;

    private QaseApiSmokeTest qaseApi;



//    @BeforeClass
//    public void report() throws IOException {
//        qaseApi = new QaseApiSmokeTest();
//        System.out.println("Before: ");
//        ID = qaseApi.testRun();
//    }





    @BeforeMethod
    void setUp() throws IOException {



/*bagian ini untuk persiapan;
* 1. persiapan link dengan metode POM, terhubung ke file
* 2. membaca isi file
* 3. mempersiapkan konfigurasi untuk koneksi ke address API\
* 4. dari ketiga proses tersebut akan menjadi satu objek (dibungkus)  */



//  #1. konek ke file dengan class properties melalui address direktori
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/option.properties"));



//  #2. membaca attribute objek di dalam file, objek properties dengan class getProperty
// attribute URL akan memiliki fungsi membaca objek file
        URL = properties.getProperty("url");



/*  #3. Persiapan untuk konek ke API website*/
/*  reqSpec merupakan konfigurasi sebelum konek ke address API
    RestAssured merupakan class statis dari library yang membangun hubungan dengan API
    .given() merupakan prasyarat untuk persiapan request API
    .baseUri() untuk root link api dan untuk endpoint API "baseUri" + "/endpoint"
    jadi; reqSpec = memiliki fungsi untuk konek ke API beserta link-nya URL*/
        requestSpecification = RestAssured.given().baseUri(URL);


/*  4. Dependensi Injection
*   objek helper memiliki class Utilities
*   kemudian diberikan parameter reqSpec
*   jadi helper memiliki dua kegunaan pertama class utility dan reqSpec*/
        helper = new Utilities(requestSpecification);
    }




    @Test
    void getDataProduct(){

/*  Tahap ini memanggil endpoint products API fakeStore
*   1. Response attribute
*   2. Response attribute diberikan objek helper yang memiliki 2 fungsi persiapan API dan Utility class
*   3. attribute helper memanggil method yang ada di class Utilities secara spefisik
*   Setiap method memiliki fungsinya sendiri dan helper sebagai trigger-nya */
        Response getProducts = helper.getLinkApi();
        System.out.println("result: " + getProducts.asPrettyString());
    }




    @org.testng.annotations.Test
    void getDataSingleProduct(){
        id = 0;
        int statusCode = 200;
        Response getSingleProduct = helper.getSingleProduct(id);
        System.out.println("RESULT: " + getSingleProduct.asPrettyString());
        helper.validateResponse(getSingleProduct, statusCode);
        System.out.println("status code " + getSingleProduct.statusCode());
        Assert.assertEquals(statusCode, 200);
    }




    @org.testng.annotations.Test
    void postCreateProduct(){


/*  Struktur kode ini adalah:

*   Map<"tipe data", Object> "nama attribute" = new HashMap(); ===> membuat attribute yang berisi objek HashMap
*   "nama attribute".put("nama key-path", "nama value-path") ==? method PUT untuk memasukan nilai


*   ==> attribute response memiliki objek class lain yaitu; utility ada fungsi post dan objek HashMap
*   Response "name attribute" = "konstruktor-class"."nama-method-di-dalam-class"("input-nama-attribute HashMap")


*   attribute response lakukan validasi struktur data json dibuat
*   response.then()
*   statusCode(200) validasi kode API jika berhasil
*   .body("nama key-path" sesuai di "nama attirbute" HashMap), org.hamcrest.Macthers.equalTo("nama value-path" di hamcrest)
*   ""nama attirbute" dan "nama value-path" sesuaikan di HashMap tujuannya data yang disusun dan akan di buat sama isinya
*/


/*  1. membuat object "key-path" dan "value-path" dengan attribute createProduct
*   sebelumnya menambahkan data type integer untuk status response */


        int statusCode = 200;

        Map<String, Object> createProduct = new HashMap<>();
        createProduct.put("title", "baju");
        createProduct.put("price", 1000);
        createProduct.put("description", "baju buatan anak bangsa");
        createProduct.put("image", "hidup anak bangsa");



/*  2. attribute response diberikan objek helper dan method ditambah data yang dibuat yaitu
*   attribute "createProduct" maka response memiliki 3 fungsi class helper, func. postProduct dan createProduct
*   ini baru tahap menyusun data dan kemudian apakah diterima oleh json yaitu responnya*/
        Response response = helper.postProduct(createProduct);
        System.out.println(response.asPrettyString());

/*  3. membuat validasi antara response yang dihasilkan dengan int attribute "status code" harapannya adalah 200
*   kemudian cetak hasil print API di bawahnya*/

        helper.validateResponse(response, statusCode);
        System.out.println("Response API: " + response.statusCode());



/*  4. attribute response membuat struktur data json dan kemudian validasi struktur dibawah ini*/
        response.then()
                .statusCode(200)
                .body("title", org.hamcrest.Matchers.equalTo("baju"))
                .body("price", org.hamcrest.Matchers.equalTo(1000))
                .body("description", org.hamcrest.Matchers.equalTo("baju buatan anak bangsa"))
                .body("image", org.hamcrest.Matchers.equalTo("hidup anak bangsa"));

    }


    @org.testng.annotations.Test
    void putUpdateDataProduct(){
/*  proses ini merupakan update data di JSON
*   1. membuat HasMap objek merupakan pasangan kata kunci
*   2. nama attribute ditambahkan method .put ex: pelangi.put
*   3. buat Library Interface Response berikan nama attribute-nya
*   4. kemudian nama attribute response masukan class objek utility.method di dalammnya
*   5. method memiliki parameter tambahkan parameter id dan objek hasMap diatas
*   6. cetak objek response*/


        id = 1;
        int statusCode = 200;
        Map<String, Object> createProduct = new HashMap<>();
        createProduct.put("title", "boneka labubu");
        createProduct.put("price", "300");
        createProduct.put("description", "boneka black pink");
        createProduct.put("image", "cute labubu");

        Response response = helper.updateProduct(id, createProduct);
        System.out.println("Response API: " + response.statusCode());
        System.out.println(response.asPrettyString());

        helper.validateResponse(response, statusCode);


    }

//    @org.junit.jupiter.api.Test


    @org.testng.annotations.Test
    void deleteProduct(){
        int id = 21;
        Response response = helper.delete(id);
        System.out.println(response.statusCode());

    }

//    @AfterClass
//    public void result() {
//        List<Integer> Id = Arrays.asList(7, 8, 9, 10);
//        String status = "passed";
//        qaseApi.updateClass(ID, Id, status);
//        System.out.println("===After - Finish===");
//
//    }


}
