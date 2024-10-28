package wb.tech.user;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import wb.tech.AuthData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@Tag("User")
@Epic("API WBTechSchool")
@Feature("Тестирование API редактирование профиля")
@DisplayName("Тест № 2: Редактирование данных пользователя")

public class EditProfileTest {

    @Test
    @DisplayName("Редактирование профиля")
    @Description("Ожидаемый результат - данные профиля изменены")
    public void editProfileIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", "Andrew");
        requestBody.put("middlename", "Алексеевич");
        requestBody.put("lastname", "Родинов");
        requestBody.put("telegram", "@an_rodinov");
        requestBody.put("email", "rodinov.andrew@gmail.com");
        requestBody.put("employer_id", 0);


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/v1/profile")
                .then()
                .statusCode(200)
                .body(containsString("Обновлена"))
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @AfterEach
    @Step("Возвращаем данные в первоначальное значение")
    public void editProfileAfter() {
        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", "Андрей");
        requestBody.put("middlename", "Алексеевич");
        requestBody.put("lastname", "Родинов");
        requestBody.put("telegram", "@an_rodinov");
        requestBody.put("email", "rodinov.andre@yandex.ru");
        requestBody.put("employer_id", 0);


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/v1/profile")
                .then()
                .statusCode(200)
                .body(containsString("Обновлена"))
                .extract()
                .response();
    }
}
