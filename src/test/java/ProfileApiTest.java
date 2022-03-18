import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ProfileApiTest {

    UsersApiService usersApiService = new UsersApiService();

    private UsersApi usersApi;
    private final int expectedStatusCode;

    public ProfileApiTest(UsersApi usersApi, int expectedStatusCode) {
        this.usersApi = usersApi;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {UsersApi.getNameOnly(), 403},
                {UsersApi.getEmailOnly(), 403},
                {UsersApi.getNameAndEmail(), 403},
                {UsersApi.getPasswordOnly(), 403},
                {UsersApi.getEmailAndPassword(), 403},
                {UsersApi.getNameAndPassword(), 403},
        };
    }

    @Test
    @DisplayName("create user if one of the required fields is not filled in")
    public void invalidRequestTest() {

        ValidatableResponse response = usersApiService.createUser(usersApi);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Status code is wrong", expectedStatusCode, statusCode);
    }
}
