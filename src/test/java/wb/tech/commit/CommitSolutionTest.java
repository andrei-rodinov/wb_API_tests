package wb.tech.commit;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import wb.tech.AuthData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@Tag("Commit")
@Epic("API WBTechSchool")
@Feature("Тестирование API отправка задания на проверку")
@DisplayName("Тест № 10: Отправка решения")

public class CommitSolutionTest {

    @Test
    @DisplayName("Отправка задания на проверку ")
    @Description("Ожидаемый результат - Задание отправлено")
    public void sendSolutionIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        String requestBody = "{ \"comment\": \"Автотестовый коммит\" }";
        String taskId = "35422";

        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/tasks/" + taskId + "/commit")
                .then()
                .statusCode(200)
                .body(containsString("Решение отправлено"))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }
}