import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    OrderService orderService = new OrderService();
    IngredientService ingredientService = new IngredientService();
    UsersService usersService = new UsersService();
    Users users;
    String token;

    @Before
    public void setUp() {
        users = new Users().getRandom();
    }

    @DisplayName("Getting a list of orders by not authorized user")
    @Test
    public void getAuthorizedOrderList() {
        token = usersService.createUser(users).extract().path("accessToken").toString().substring(10);
        ValidatableResponse response  = orderService.GetOrderListAuthorizedUser(token);
        int statusCode = response.extract().statusCode();
        assertEquals("Getting a list of orders by an authorized user", 403, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

    @Test
    @DisplayName("create order not authorized user")
    public void createOrderNotAuthorizedUser() {
        ValidatableResponse response = orderService.GetOrderListNotAuthorizedUser();
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);

    }
    @Step("get invalid list ingredient")
    public ArrayList<String> getInvalidIngredient() {
        ArrayList<String> ingredient = new ArrayList<String>();
        ingredient.add(RandomStringUtils.randomAlphanumeric(10));
        return ingredient;
    }

    @Test
    @DisplayName("Changing user data with an invalid hash of ingredients")
    public void placeOrderWithInvalidIngredients() {
        ValidatableResponse response = orderService
                .makeOrderNotAuthorized(new Ingredient(ingredientService.getInvalidIngredients()));
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Internal Server Error", 500, statusCode);
    }

    @Test
    @DisplayName("get order list not authorized user")
    public void getUnauthorizedOrderList() {
        ValidatableResponse response  = orderService.GetOrderListNotAuthorizedUser();
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("email or password are incorrect", 401, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

}
