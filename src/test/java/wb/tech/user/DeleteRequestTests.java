package wb.tech.user;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import wb.tech.AuthData;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@Tag("User")
@Epic("API WBTechSchool")
@Feature("Тестирование API заявки на удаление профиля")
@DisplayName("Тест № 3: Создание заявки на удаление / Отмена заявки на удаление")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DeleteRequestTests {


    @Test
    @Order(1)
    @DisplayName("Заявка на удаление профиля")
    @Description("Ожидаемый результат - создана заявка на удаление профиля")
    public void profileDeleteRequestIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/profile/delete-request")
                .then()
                .statusCode(200)
                .body(containsString("Заявка на удаление профиля создана"))
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @Test
    @Order(2)
    @DisplayName("Отмена заявки на удаление профиля")
    @Description("Ожидаемый результат - заявка на удаление профиля отменена")
    public void profileAbortDeleteRequestIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/profile/delete-request")
                .then()
                .statusCode(200)
                .body(containsString("Заявка на удаление профиля отменена"))
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
}
