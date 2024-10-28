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
import wb.tech.AuthData;


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

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

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

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

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

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

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

        String cookie = AuthData.AUTH_COOKIE;
        String authorization = AuthData.AUTH_TOKEN;

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