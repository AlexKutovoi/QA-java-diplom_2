public class UsersApiCredentials {

    public final String email;
    public final String password;

    public UsersApiCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UsersApiCredentials from(UsersApi usersApi){
        return new UsersApiCredentials(usersApi.email, usersApi.password);
    }
}

