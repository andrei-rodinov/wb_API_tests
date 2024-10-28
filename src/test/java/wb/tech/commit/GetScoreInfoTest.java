package wb.tech.commit;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wb.tech.AuthData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("Commit")
@Epic("API WBTechSchool")
@Feature("Тестирование API получение информации об оценках")
@DisplayName("Тест № 9: Информация об оценках")

public class GetScoreInfoTest {

    @ParameterizedTest
    @CsvSource({
            "35422",  // id
            "35423"   // id
    })
    @DisplayName("Получение информации об оценках")
    @Description("Ожидаемый результат - Информация отображена")
    public void getScoreInfoById(int id) {

        RestAssured.baseURI = "https://tech.wildberries.ru";

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;


        Response response = given()
                .header("Cookie", cookie)
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/tasks/" + id + "/commit")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }
}
