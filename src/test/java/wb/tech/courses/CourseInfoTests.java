package wb.tech.courses;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@Tag("Courses")
@Epic("API WBTechSchool")
@Feature("Тестирование API получение информации о курсах")
@DisplayName("Тест № 6: Информация о курсах")

public class CourseInfoTests {

    @Test
    @Order(1)
    @DisplayName("Получение информации о курсах")
    @Description("Ожидаемый результат - Информация отображена")
    public void getCoursesInfo() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = "DeviceId=226c41ea-672e-423b-aa3e-d90e2a9cf8c6; UserIdToken=eyJhbGciOiJIUzI1NiIsImtpbmQiOiJhY2Nlc3MiLCJ0eXAiOiJKV1QifQ.eyJ1c2VyaWQiOjU4ODAsIndiX3VzZXJfaWQiOiIyNjA0MDc5NyIsInN1YiI6IklEIiwiZXhwIjoxNzMyMjAwMTM4LCJpYXQiOjE3Mjk2MDgxMzgsImp0aSI6ImU4NmI3OTNjLTE0ZmQtNGJjZC1hNjJjLWNiOWZjNWY3NTNkNiJ9.kFvhxOv_-lb5mboYDEBFTMs1c0SKNKw3hKW1ACodyhw; wbx-validation-key=784ddfaf-78e2-4124-850b-03cf4d15c092;";
        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk2MDgxMzgsInZlcnNpb24iOjIsInVzZXIiOiIyNjA0MDc5NyIsInNoYXJkX2tleSI6IjE4IiwiY2xpZW50X2lkIjoid2J0ZWNoIiwic2Vzc2lvbl9pZCI6IjdlOTM4OTZmOTc0ZDQ4ZDhiYjMwYThjNTQ0NzU0NzdkIiwidXNlcl9yZWdpc3RyYXRpb25fZHQiOjE2OTUwMzQ4ODMsInZhbGlkYXRpb25fa2V5IjoiZTMzOTVlMWM0MWE1ZGM5YWM1MGE0YjM0NzcyZDUzN2U2NzkyMmI0M2EzN2EzZDQ0NDI5ZGI3YTMyMWI0ZmIyOSJ9.FfXt-Vhxiq2xgOsjB_GizWAaFubZ_be6MpP5C2itO075mfsfD4lZBUur1qLkpS8Da4HkecL_68ce3VVyM5yPPhPQo48_1jPmg_rvRcI8Of1k7o5zdUTG2OcyG9qJEvH1jAWXWxAk9Uj15sAOIKKPd0lG60SKJXhWh1nb4EwbfjQhWAmO667CoKGIK0Oz7olBMhSZSqYXV44QcyLq37Ncoy1-u7hYEu4yqJ5xL39A6GnTL-G-KYgOautpAU4guApy_rVbGgvQ2rBF8bm6Bq226Pfo7HWnoHDOazXEE4Qo3gKr7SNioJ9F_Oy6FBgSY5jHEEcJ2UTmlxkTebsALIRWYA";


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/public/courses")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 10, 0",  // category_id = 1, limit = 10, offset = 0
            "2, 5, 0",   // category_id = 2, limit = 5, offset = 0
            "1, 10, 10"  // category_id = 1, limit = 10, offset = 10
    })
    @Order(2)
    @DisplayName("Получение информации о курсах с выборкой")
    @Description("Ожидаемый результат - Информация отображена согласно запросу")
    public void getCoursesInfoWithParams(int categoryId, int limit, int offset) {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = "DeviceId=226c41ea-672e-423b-aa3e-d90e2a9cf8c6; UserIdToken=eyJhbGciOiJIUzI1NiIsImtpbmQiOiJhY2Nlc3MiLCJ0eXAiOiJKV1QifQ.eyJ1c2VyaWQiOjU4ODAsIndiX3VzZXJfaWQiOiIyNjA0MDc5NyIsInN1YiI6IklEIiwiZXhwIjoxNzMyMjAwMTM4LCJpYXQiOjE3Mjk2MDgxMzgsImp0aSI6ImU4NmI3OTNjLTE0ZmQtNGJjZC1hNjJjLWNiOWZjNWY3NTNkNiJ9.kFvhxOv_-lb5mboYDEBFTMs1c0SKNKw3hKW1ACodyhw; wbx-validation-key=784ddfaf-78e2-4124-850b-03cf4d15c092;";
        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk2MDgxMzgsInZlcnNpb24iOjIsInVzZXIiOiIyNjA0MDc5NyIsInNoYXJkX2tleSI6IjE4IiwiY2xpZW50X2lkIjoid2J0ZWNoIiwic2Vzc2lvbl9pZCI6IjdlOTM4OTZmOTc0ZDQ4ZDhiYjMwYThjNTQ0NzU0NzdkIiwidXNlcl9yZWdpc3RyYXRpb25fZHQiOjE2OTUwMzQ4ODMsInZhbGlkYXRpb25fa2V5IjoiZTMzOTVlMWM0MWE1ZGM5YWM1MGE0YjM0NzcyZDUzN2U2NzkyMmI0M2EzN2EzZDQ0NDI5ZGI3YTMyMWI0ZmIyOSJ9.FfXt-Vhxiq2xgOsjB_GizWAaFubZ_be6MpP5C2itO075mfsfD4lZBUur1qLkpS8Da4HkecL_68ce3VVyM5yPPhPQo48_1jPmg_rvRcI8Of1k7o5zdUTG2OcyG9qJEvH1jAWXWxAk9Uj15sAOIKKPd0lG60SKJXhWh1nb4EwbfjQhWAmO667CoKGIK0Oz7olBMhSZSqYXV44QcyLq37Ncoy1-u7hYEu4yqJ5xL39A6GnTL-G-KYgOautpAU4guApy_rVbGgvQ2rBF8bm6Bq226Pfo7HWnoHDOazXEE4Qo3gKr7SNioJ9F_Oy6FBgSY5jHEEcJ2UTmlxkTebsALIRWYA";


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .queryParam("category_id", categoryId)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .when()
                .get("/api/v1/public/courses")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

    @ParameterizedTest
    @CsvSource({
            "junior, 2",  // level = junior, category_id = 2
            "middle, 1",   // level = middle, category_id = 1
    })
    @Order(3)
    @DisplayName("Получение информации о курсах по уровню")
    @Description("Ожидаемый результат - Информация отображена согласно запросу")
    public void getCoursesInfoByLevel(String level, int categoryId) {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = "DeviceId=226c41ea-672e-423b-aa3e-d90e2a9cf8c6; UserIdToken=eyJhbGciOiJIUzI1NiIsImtpbmQiOiJhY2Nlc3MiLCJ0eXAiOiJKV1QifQ.eyJ1c2VyaWQiOjU4ODAsIndiX3VzZXJfaWQiOiIyNjA0MDc5NyIsInN1YiI6IklEIiwiZXhwIjoxNzMyMjAwMTM4LCJpYXQiOjE3Mjk2MDgxMzgsImp0aSI6ImU4NmI3OTNjLTE0ZmQtNGJjZC1hNjJjLWNiOWZjNWY3NTNkNiJ9.kFvhxOv_-lb5mboYDEBFTMs1c0SKNKw3hKW1ACodyhw; wbx-validation-key=784ddfaf-78e2-4124-850b-03cf4d15c092;";
        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk2MDgxMzgsInZlcnNpb24iOjIsInVzZXIiOiIyNjA0MDc5NyIsInNoYXJkX2tleSI6IjE4IiwiY2xpZW50X2lkIjoid2J0ZWNoIiwic2Vzc2lvbl9pZCI6IjdlOTM4OTZmOTc0ZDQ4ZDhiYjMwYThjNTQ0NzU0NzdkIiwidXNlcl9yZWdpc3RyYXRpb25fZHQiOjE2OTUwMzQ4ODMsInZhbGlkYXRpb25fa2V5IjoiZTMzOTVlMWM0MWE1ZGM5YWM1MGE0YjM0NzcyZDUzN2U2NzkyMmI0M2EzN2EzZDQ0NDI5ZGI3YTMyMWI0ZmIyOSJ9.FfXt-Vhxiq2xgOsjB_GizWAaFubZ_be6MpP5C2itO075mfsfD4lZBUur1qLkpS8Da4HkecL_68ce3VVyM5yPPhPQo48_1jPmg_rvRcI8Of1k7o5zdUTG2OcyG9qJEvH1jAWXWxAk9Uj15sAOIKKPd0lG60SKJXhWh1nb4EwbfjQhWAmO667CoKGIK0Oz7olBMhSZSqYXV44QcyLq37Ncoy1-u7hYEu4yqJ5xL39A6GnTL-G-KYgOautpAU4guApy_rVbGgvQ2rBF8bm6Bq226Pfo7HWnoHDOazXEE4Qo3gKr7SNioJ9F_Oy6FBgSY5jHEEcJ2UTmlxkTebsALIRWYA";


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .queryParam("level", level)
                .queryParam("category_id", categoryId)
                .when()
                .get("/api/v1/public/courses")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

    @ParameterizedTest
    @CsvSource({
            "8",  // id = 8
            "10",  // id = 10
            "11",  // id = 11
            "4",  // id = 4
    })
    @Order(4)
    @DisplayName("Получение информации о курсах по ID")
    @Description("Ожидаемый результат - Информация отображена согласно запросу")
    public void getCoursesInfoByID(int id) {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = "DeviceId=226c41ea-672e-423b-aa3e-d90e2a9cf8c6; UserIdToken=eyJhbGciOiJIUzI1NiIsImtpbmQiOiJhY2Nlc3MiLCJ0eXAiOiJKV1QifQ.eyJ1c2VyaWQiOjU4ODAsIndiX3VzZXJfaWQiOiIyNjA0MDc5NyIsInN1YiI6IklEIiwiZXhwIjoxNzMyMjAwMTM4LCJpYXQiOjE3Mjk2MDgxMzgsImp0aSI6ImU4NmI3OTNjLTE0ZmQtNGJjZC1hNjJjLWNiOWZjNWY3NTNkNiJ9.kFvhxOv_-lb5mboYDEBFTMs1c0SKNKw3hKW1ACodyhw; wbx-validation-key=784ddfaf-78e2-4124-850b-03cf4d15c092;";
        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk2MDgxMzgsInZlcnNpb24iOjIsInVzZXIiOiIyNjA0MDc5NyIsInNoYXJkX2tleSI6IjE4IiwiY2xpZW50X2lkIjoid2J0ZWNoIiwic2Vzc2lvbl9pZCI6IjdlOTM4OTZmOTc0ZDQ4ZDhiYjMwYThjNTQ0NzU0NzdkIiwidXNlcl9yZWdpc3RyYXRpb25fZHQiOjE2OTUwMzQ4ODMsInZhbGlkYXRpb25fa2V5IjoiZTMzOTVlMWM0MWE1ZGM5YWM1MGE0YjM0NzcyZDUzN2U2NzkyMmI0M2EzN2EzZDQ0NDI5ZGI3YTMyMWI0ZmIyOSJ9.FfXt-Vhxiq2xgOsjB_GizWAaFubZ_be6MpP5C2itO075mfsfD4lZBUur1qLkpS8Da4HkecL_68ce3VVyM5yPPhPQo48_1jPmg_rvRcI8Of1k7o5zdUTG2OcyG9qJEvH1jAWXWxAk9Uj15sAOIKKPd0lG60SKJXhWh1nb4EwbfjQhWAmO667CoKGIK0Oz7olBMhSZSqYXV44QcyLq37Ncoy1-u7hYEu4yqJ5xL39A6GnTL-G-KYgOautpAU4guApy_rVbGgvQ2rBF8bm6Bq226Pfo7HWnoHDOazXEE4Qo3gKr7SNioJ9F_Oy6FBgSY5jHEEcJ2UTmlxkTebsALIRWYA";


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/courses/" + id)
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

}