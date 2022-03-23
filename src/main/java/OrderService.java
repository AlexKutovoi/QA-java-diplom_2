import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderService extends Order {

    @Step("make an order not authorized user")
    public ValidatableResponse makeOrderNotAuthorized(Ingredient ingredient) {
        return given()
                .spec(getBaseSpec())
                .body(ingredient)
                .when()
                .post(UsersService.BASE_URL + "api/orders")
                .then();
    }

    @Step("make an order authorized user")
    public ValidatableResponse makeOderAuthorized(String token, Ingredient ingredient) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(ingredient)
                .when()
                .post(UsersService.BASE_URL + "api/orders")
                .then();
    }

    @Step("get order list authorized user")
    public ValidatableResponse GetOrderListAuthorizedUser(String token) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .when()
                .get(UsersService.BASE_URL + "api/orders")
                .then();
    }

    @Step("get order list not authorized user")
    public ValidatableResponse GetOrderListNotAuthorizedUser() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(UsersService.BASE_URL + "api/orders")
                .then();
    }
}
