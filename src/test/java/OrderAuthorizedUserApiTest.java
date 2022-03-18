import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderAuthorizedUserApiTest {

    OrderServiceApi orderServiceApi = new OrderServiceApi();
    IngredientApi ingredientApi = new IngredientApi();
    UsersApiService usersApiService = new UsersApiService();
    UsersApi usersApi;
    String token;

    @Before
    public void setUp() {
        usersApi = new UsersApi().getRandom();
    }

    @Test
    @DisplayName("create order authorized user")
    public void placeAuthorizedOrder() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = orderServiceApi.makeOderAuthorized(token, ingredientApi);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("invalid code", 400, statusCode);
    }

    @DisplayName("get list order auuthorized user")
    @Test
    public void getAuthorizedOrderList() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response  = orderServiceApi.GetOrderListAuthorizedUser(token);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("invalid code", 200, statusCode);

    }

    @After
    public void tearDown() {
        usersApiService.delete(token);
    }
}
