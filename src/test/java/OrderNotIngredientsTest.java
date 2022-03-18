import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderNotIngredientsTest {

    OrderServiceApi orderServiceApi = new OrderServiceApi();
    UsersApi usersApi;

    @Before
    public void setUp() {
        usersApi = new UsersApi().getRandom();
    }
    @Test
    @DisplayName("Changing user data without ingredients")
    public void placeOrderWithoutIngredients() {
        ValidatableResponse response = orderServiceApi.makeOrderNotAuthorized(new IngredientApi());
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Ingredient ids must be provided", 400, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }
}
