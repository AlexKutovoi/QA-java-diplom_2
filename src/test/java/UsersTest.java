import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsersTest {

    private Users usersApi;
    private UsersCredentials usersApiCredentials;
    private UsersService usersApiService;
    String token;

    @Before
    public void setUp() {
        usersApi = new Users().getRandom();
        usersApiCredentials = new UsersCredentials(usersApi.email, usersApi.password);
        usersApiService = new UsersService();
    }
    @Test
    @DisplayName("creating a unique user")
    public void createRequestReturnsSuccessTest() {
        ValidatableResponse response = usersApiService.createUser(usersApi);
        token =  response.extract().path("accessToken").toString().substring(10);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Successful creating unique user", 200, statusCode);
    }

    @Test
    @DisplayName("create user already exists")
    public void doubleRequestReturnsErrorTest() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(10);
        ValidatableResponse response = usersApiService.createUser(usersApi);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("User already exists", 403, statusCode);
    }

    @Test
    @DisplayName("login existing user")
    public void loginTest() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(10);
        ValidatableResponse response = usersApiService.authorizedUser(usersApiCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Successful login", 200, statusCode);
        usersApiService.delete(token);
    }

    @Test
    @DisplayName("edit authorized user")
    public void editProfileTest() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(7);
        usersApiService = new UsersService();
        usersApiCredentials = new UsersCredentials(usersApi.email, usersApi.password);
        ValidatableResponse response = usersApiService.editAuthorizedUser(usersApiCredentials, token);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Successful edit", 200, statusCode);
    }

    @Test
    @DisplayName("edit not authorized user")
    public void unauthorizedEditProfileTest() {
        token = usersApiService.createUser(usersApi).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = usersApiService.editNotAuthorizedUser(usersApiCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Incorrect edit", 401, statusCode);
    }

    @After
    @Step("delete user")
    public void tearDown() {

        usersApiService.delete(token);
    }
}
