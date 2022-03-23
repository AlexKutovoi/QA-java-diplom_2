import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderAuthorizedUserTest {

    OrderService orderService = new OrderService();
    Ingredient ingredient = new Ingredient();
    UsersService usersService = new UsersService();
    Users users;
    String token;

    @Before
    public void setUp() {
        users = new Users().getRandom();
    }

    @Test
    @DisplayName("create order authorized user")
    public void placeAuthorizedOrder() {
        token = usersService.createUser(users).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = orderService.makeOderAuthorized(token, ingredient);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("invalid code", 400, statusCode);
    }

    @DisplayName("get list order auuthorized user")
    @Test
    public void getAuthorizedOrderList() {
        token = usersService.createUser(users).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response  = orderService.GetOrderListAuthorizedUser(token);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("invalid code", 200, statusCode);

    }

    @After
    public void tearDown() {
        usersService.delete(token);
    }
}
