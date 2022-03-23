public class UsersCredentials {

    public String email;
    public  String password;

    public UsersCredentials() {

    }

    public UsersCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public static UsersCredentials from(Users usersApi){
        return new UsersCredentials(usersApi.email, usersApi.password);
    }
}

