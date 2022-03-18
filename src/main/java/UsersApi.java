import org.apache.commons.lang3.RandomStringUtils;

public class UsersApi {
    public String name;
    public String email;
    public String password;

    public UsersApi() {

    }

    public UsersApi (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public static UsersApi getRandom() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new UsersApi(name, email, password);
    }

    public UsersApi setName(String name) {
        this.name = name;
        return this;
    }

    public UsersApi setEmail(String email) {
        this.email = email;
        return this;
    }

    public UsersApi setPassword(String password) {
        this.password = password;
        return this;
    }

    public static UsersApi getNameOnly() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new UsersApi().setName(name);
    }

    public static UsersApi getEmailOnly() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@@kali.com";
        return new UsersApi().setEmail(email);
    }

    public static UsersApi getNameAndEmail() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        return new UsersApi().setName(name).setEmail(email);
    }

    public static UsersApi getPasswordOnly() {
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new UsersApi().setPassword(password);
    }

    public static UsersApi getEmailAndPassword() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new UsersApi().setEmail(email).setPassword(password);
    }

    public static UsersApi getNameAndPassword() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new UsersApi().setName(name).setPassword(password);
    }

}
