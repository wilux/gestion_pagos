package net.neflores.model;


//@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    private String password;

    private String confirmPassword;

    
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}