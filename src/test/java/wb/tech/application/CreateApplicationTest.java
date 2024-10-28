package wb.tech.application;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@Tag("Application")
@Epic("API WBTechSchool")
@Feature("Тестирование API создание записи на курс")
@DisplayName("Тест № 1: Создание новой записи на курс")

public class CreateApplicationTest {

    @Test
    @DisplayName("Создание записи на курс \"Быстрый RUST\" ")
    @Description("Ожидаемый результат - Заявка создана")
    public void createNewApplicationIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = "DeviceId=226c41ea-672e-423b-aa3e-d90e2a9cf8c6; UserIdToken=eyJhbGciOiJIUzI1NiIsImtpbmQiOiJhY2Nlc3MiLCJ0eXAiOiJKV1QifQ.eyJ1c2VyaWQiOjU4ODAsIndiX3VzZXJfaWQiOiIyNjA0MDc5NyIsInN1YiI6IklEIiwiZXhwIjoxNzMyMjAwMTM4LCJpYXQiOjE3Mjk2MDgxMzgsImp0aSI6ImU4NmI3OTNjLTE0ZmQtNGJjZC1hNjJjLWNiOWZjNWY3NTNkNiJ9.kFvhxOv_-lb5mboYDEBFTMs1c0SKNKw3hKW1ACodyhw; wbx-validation-key=784ddfaf-78e2-4124-850b-03cf4d15c092;";
        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3Mjk2MDgxMzgsInZlcnNpb24iOjIsInVzZXIiOiIyNjA0MDc5NyIsInNoYXJkX2tleSI6IjE4IiwiY2xpZW50X2lkIjoid2J0ZWNoIiwic2Vzc2lvbl9pZCI6IjdlOTM4OTZmOTc0ZDQ4ZDhiYjMwYThjNTQ0NzU0NzdkIiwidXNlcl9yZWdpc3RyYXRpb25fZHQiOjE2OTUwMzQ4ODMsInZhbGlkYXRpb25fa2V5IjoiZTMzOTVlMWM0MWE1ZGM5YWM1MGE0YjM0NzcyZDUzN2U2NzkyMmI0M2EzN2EzZDQ0NDI5ZGI3YTMyMWI0ZmIyOSJ9.FfXt-Vhxiq2xgOsjB_GizWAaFubZ_be6MpP5C2itO075mfsfD4lZBUur1qLkpS8Da4HkecL_68ce3VVyM5yPPhPQo48_1jPmg_rvRcI8Of1k7o5zdUTG2OcyG9qJEvH1jAWXWxAk9Uj15sAOIKKPd0lG60SKJXhWh1nb4EwbfjQhWAmO667CoKGIK0Oz7olBMhSZSqYXV44QcyLq37Ncoy1-u7hYEu4yqJ5xL39A6GnTL-G-KYgOautpAU4guApy_rVbGgvQ2rBF8bm6Bq226Pfo7HWnoHDOazXEE4Qo3gKr7SNioJ9F_Oy6FBgSY5jHEEcJ2UTmlxkTebsALIRWYA";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("course_id", 5);
        requestBody.put("description", "rust");
        requestBody.put("is_employee_reserve", false);
        requestBody.put("lastname", null);
        requestBody.put("firstname", "Ivanov");
        requestBody.put("employer_id", null);
        requestBody.put("resumelink", null);
        requestBody.put("github_link", null);
        requestBody.put("phone", null);
        requestBody.put("email", null);
        requestBody.put("telegram", null);
        requestBody.put("", "on");


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/applications")
                .then()
                .statusCode(200)
                .body(containsString("Заявка успешно создана"))
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
}
