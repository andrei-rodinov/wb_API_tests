package wb.tech.user;

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

@Tag("User")
@Epic("API WBTechSchool")
@Feature("Тестирование API редактирование профиля")
@DisplayName("Тест № 5: Принятие пользовательского соглашения")

public class AcceptAgreementTest {
    @Test
    @DisplayName("Принятие пользовательского соглашения")
    @Description("Пользовательское соглашение принято")
    public void acceptAgreementIsSuccess() {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/me/agreement")
                .then()
                .statusCode(200)
                .body(containsString("Статус соглашения принят"))
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
}
