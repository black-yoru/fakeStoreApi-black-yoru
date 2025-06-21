package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.com.utility.Utilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetFakeStore {

    private String URL;
    private int id;
    private int[] ids;

//request untuk konfigurasi sebelum permintaan dikirim dan untuk test lain menggunakan API yang sama
//tanpa perlu membuat class RestAssured berulang. cukup satu kali
    private RequestSpecification requestSpecification;

    private Utilities helper;





    @BeforeClass
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


    @Test
    void getDataSingleProduct(){
        id = 1;
        int[] IDS= {2,3,4};
        Response getSingleProduct = helper.getSingleProduct(id, IDS);
        System.out.println("RESULT: " + getSingleProduct.asPrettyString());
    }
}
