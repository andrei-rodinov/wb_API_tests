package wb.tech.tasks;

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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("Tasks")
@Epic("API WBTechSchool")
@Feature("Тестирование API получение информации о задачах")
@DisplayName("Тест № 8: Информация о задачах")

public class GetTasksTest {

    @Test
    @DisplayName("Получение информации о задачах")
    @Description("Ожидаемый результат - Информация отображена")
    public void getAllTasks() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/tasks")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }

}
