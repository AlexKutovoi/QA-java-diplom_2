import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginErrorApiTest {

    private UsersApi usersApi;
    private UsersApiCredentials usersApiCredentials;
    private UsersApiService usersApiService;

    @Before
    public void setUp() {
        usersApi = new UsersApi().getRandom();
        usersApiCredentials = new UsersApiCredentials(usersApi.email, usersApi.password);
        usersApiService = new UsersApiService();
    }

    @Test
    @DisplayName("login with invalid username and password")
    public void invalidUserLoginTest() {
        ValidatableResponse response = usersApiService.authorizedUser(usersApiCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("email or password are incorrect", 401, statusCode);
    }
}
