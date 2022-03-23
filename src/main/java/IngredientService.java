import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class IngredientService extends Order {

    @Step("get list ingredient")
    public ArrayList getIngredient() {
        ArrayList<String> ingredient = given()
                .spec(getBaseSpec())
                .when()
                .get(UsersService.BASE_URL + "api/ingredients")
                .then()
                .extract()
                .path("data._id");
        return ingredient;
    }

    @Step("Получение некорректного списка ингредиентов")
    public ArrayList getInvalidIngredients() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add(RandomStringUtils.randomAlphanumeric(10));
        return ingredients;
    }
}
