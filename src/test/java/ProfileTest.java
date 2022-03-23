import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ProfileTest {

    UsersService usersService = new UsersService();

    private Users users;
    private final int expectedStatusCode;

    public ProfileTest(Users users, int expectedStatusCode) {
        this.users = users;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {Users.getNameOnly(), 403},
                {Users.getEmailOnly(), 403},
                {Users.getNameAndEmail(), 403},
                {Users.getPasswordOnly(), 403},
                {Users.getEmailAndPassword(), 403},
                {Users.getNameAndPassword(), 403},
        };
    }

    @Test
    @DisplayName("create user if one of the required fields is not filled in")
    public void invalidRequestTest() {

        ValidatableResponse response = usersService.createUser(users);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Status code is wrong", expectedStatusCode, statusCode);
    }
}
