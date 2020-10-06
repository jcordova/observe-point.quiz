package observe_point.quiz;

import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class Validate {

    @Step("Response's'status code {code} for endpoint : {endpoint}")
    public static void response_status_code(String endpoint, int code) {
        int actualCode = RestApiClient.responseAsObject(endpoint).getStatusCode();
        assertThat(actualCode).isEqualTo(code);
    }

    @Step("Response's body not empty for end-point : {endpoint}")
    public static void response_body_not_empty(String endpoint) {
        String jsonValues = RestApiClient.responseBodyAsString(endpoint);
        assertThat(jsonValues).isNotEmpty();
    }
}
