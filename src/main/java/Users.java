import org.apache.commons.lang3.RandomStringUtils;

public class Users {
    public String name;
    public String email;
    public String password;

    public Users() {

    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public static Users getRandom() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Users(name, email, password);
    }

    public Users setName(String name) {
        this.name = name;
        return this;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public static Users getNameOnly() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new Users().setName(name);
    }

    public static Users getEmailOnly() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@@kali.com";
        return new Users().setEmail(email);
    }

    public static Users getNameAndEmail() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        return new Users().setName(name).setEmail(email);
    }

    public static Users getPasswordOnly() {
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Users().setPassword(password);
    }

    public static Users getEmailAndPassword() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@kali.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Users().setEmail(email).setPassword(password);
    }

    public static Users getNameAndPassword() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Users().setName(name).setPassword(password);
    }

}
