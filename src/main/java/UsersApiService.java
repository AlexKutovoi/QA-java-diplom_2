import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UsersApiService extends OrderServiceApi{

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Step("create user")
    public ValidatableResponse createUser(UsersApi user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(BASE_URL + "api/auth/register")
                .then();
    }

    @Step("authorized user")
    public ValidatableResponse authorizedUser(UsersApiCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(BASE_URL + "api/auth/login")
                .then();
    }

    @Step("edit authorized user")
    public ValidatableResponse editAuthorizedUser(UsersApiCredentials credentials, String token) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(credentials)
                .when()
                .patch(BASE_URL + "api/auth/user")
                .then();
    }

    @Step("edit not authorized user")
    public ValidatableResponse editNotAuthorizedUser(UsersApiCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .patch(BASE_URL + "api/auth/user")
                .then();
    }

    @Step("delete user")
    public void delete(String token) {
        given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(token)
                .when()
                .delete(BASE_URL + "api/auth/user");
    }

}
