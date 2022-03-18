import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderApiTest {

    OrderServiceApi orderServiceApi = new OrderServiceApi();
    IngredientApi ingredientApi = new IngredientApi();
    UsersApiService usersApiService = new UsersApiService();
    UsersApi usersApi;
    String token;

    @Before
    public void setUp() {
        usersApi = new UsersApi().getRandom();
    }

    @DisplayName("Getting a list of orders by not authorized user")
    @Test
    public void getAuthorizedOrderList() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(10);
        ValidatableResponse response  = orderServiceApi.GetOrderListAuthorizedUser(token);
        int statusCode = response.extract().statusCode();
        assertEquals("Getting a list of orders by an authorized user", 403, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

    @Test
    @DisplayName("create order not authorized user")
    public void createOrderNotAuthorizedUser() {
        ValidatableResponse response = orderServiceApi.GetOrderListNotAuthorizedUser();
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);

    }

    @Test
    @DisplayName("Changing user data with an invalid hash of ingredients")
    public void placeOrderWithInvalidIngredients() {
        ValidatableResponse response = orderServiceApi
                .makeOrderNotAuthorized(new IngredientApi(orderServiceApi.getInvalidIngredient()));
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Internal Server Error", 500, statusCode);
    }

    @Test
    @DisplayName("get order list not authorized user")
    public void getUnauthorizedOrderList() {
        ValidatableResponse response  = orderServiceApi.GetOrderListNotAuthorizedUser();
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("email or password are incorrect", 401, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

}
