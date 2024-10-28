package wb.tech.application;

import wb.tech.AuthData;
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

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

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
