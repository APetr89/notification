package ru.dex.api.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.URLENC;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static ru.dex.api.helpers.CustomAllureListener.withCustomTemplates;


public class Specifications {

    public static RequestSpecification requestSpecOnlyMobileToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "mobile.client")
                .formParam("client_secret", "D7927BE0-A841-414C-880E-206D08235B6D")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access mobile-api")
                .formParam("username", "77777777777")
                .formParam("password", "Passw0rd%")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpecOnlyToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }


    public static RequestSpecification requestSpecWithoutTokenProfile(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpec(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                //   .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }


    public static RequestSpecification requestSpecAdminImage(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                // .setContentType(JSON)//---> Установка Content Type
                // .setAccept(JSON)//---> Установка Accept
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpecForAuth(String url) {

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(URLENC)//---> Установка Content Type
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }


    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }


    public static ResponseSpecification responseSpecOK200WithoutJson() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)//---> Уровень логирования
                .expectStatusCode(SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }


    public static ResponseSpecification responseSpecDelete() {
        return new ResponseSpecBuilder()
                .build();
    }

    public static ResponseSpecification responseSpecUpdate() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectStatusCode(SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }


    public static ResponseSpecification responseSpecBadRequest400() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(SC_BAD_REQUEST)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }


    public static ResponseSpecification responseSpecNotFound404() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(SC_NOT_FOUND)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecConflict409() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(SC_CONFLICT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpec400WithoutJson() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(SC_BAD_REQUEST)
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)
                .build();
    }


    public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installRequestSpecification(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installResponseSpecification(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }

}
