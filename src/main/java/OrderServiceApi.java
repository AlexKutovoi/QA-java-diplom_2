import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class OrderServiceApi extends OrderApi {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Step("get list ingredient")
    public ArrayList getIngredient() {
        ArrayList<String> ingredient = given()
                .spec(getBaseSpec())
                .when()
                .get(BASE_URL + "api/ingredients")
                .then()
                .extract()
                .path("data._id");
        return ingredient;
    }

    @Step("get invalid list ingredient")
    public ArrayList<String> getInvalidIngredient() {
        ArrayList<String> ingredient = new ArrayList<String>();
        ingredient.add(RandomStringUtils.randomAlphanumeric(10));
        return ingredient;
    }

    @Step("make an order not authorized user")
    public ValidatableResponse makeOrderNotAuthorized(IngredientApi ingredient) {
        return given()
                .spec(getBaseSpec())
                .body(ingredient)
                .when()
                .post(BASE_URL + "api/orders")
                .then();
    }

    @Step("make an order authorized user")
    public ValidatableResponse makeOderAuthorized(String token, IngredientApi ingredient) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(ingredient)
                .when()
                .post(BASE_URL + "api/orders")
                .then();
    }

    @Step("get order list authorized user")
    public ValidatableResponse GetOrderListAuthorizedUser(String token) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .when()
                .get(BASE_URL + "api/orders")
                .then();
    }

    @Step("get order list not authorized user")
    public ValidatableResponse GetOrderListNotAuthorizedUser() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(BASE_URL + "api/orders")
                .then();
    }
}
