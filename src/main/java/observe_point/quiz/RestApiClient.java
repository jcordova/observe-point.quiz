package observe_point.quiz;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;

public final class RestApiClient {

    private static final Logger LOGGER = LogManager.getLogger(RestApiClient.class.getName());

    @Step("Response for {endpoint}")
    public static Response responseAsObject(String endpoint) {
        defaultParser = Parser.JSON;
        return given()
                .filter(new AllureRestAssured())
                .header(CoreConstant.RAPID_HOST_KEY, CoreConstant.RAPID_HOST_VALUE)
                .header(CoreConstant.RAPID_API_KEY, CoreConstant.RAPID_API_VALUE)
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .extract()
                .response();
    }

    @Step("Response for {path}")
    public ArrayList<Integer> getArrayListNumbers(String path) {
        defaultParser = Parser.JSON;
        return given()
                .filter(new AllureRestAssured())
                .header(CoreConstant.RAPID_HOST_KEY, CoreConstant.RAPID_HOST_VALUE)
                .header(CoreConstant.RAPID_API_KEY, CoreConstant.RAPID_API_VALUE)
                .when()
                .get(CoreConstant.RAPID_API_ENDPOINT)
                .then()
                .contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .extract()
                .response()
                .andReturn()
                .getBody()
                .jsonPath()
                .get(path);
    }

    @Step("Response for {0}")
    public static JsonPath responseAsJsonPath(String endpoint) {
        LOGGER.info("API Response based on List of Array Numbers. !!!");
        defaultParser = Parser.JSON;
        return given()
                .filter(new AllureRestAssured())
                .header(CoreConstant.RAPID_HOST_KEY, CoreConstant.RAPID_HOST_VALUE)
                .header(CoreConstant.RAPID_API_KEY, CoreConstant.RAPID_API_VALUE)
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .extract()
                .response()
                .andReturn()
                .getBody()
                .jsonPath();
    }

    @Step("Response for {endpoint}")
    public static String responseBodyAsString(String endpoint) {
        defaultParser = Parser.JSON;
        return given()
                .filter(new AllureRestAssured())
                .header(CoreConstant.RAPID_HOST_KEY, CoreConstant.RAPID_HOST_VALUE)
                .header(CoreConstant.RAPID_API_KEY, CoreConstant.RAPID_API_VALUE)
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .assertThat().statusCode(200)
                .extract()
                .response()
                .toString();
    }
}
