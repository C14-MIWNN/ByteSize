package nl.miwnn.se14.bytesize.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Heron
 * Captures the information needed for the form to create a new user
 */
public class ByteSizeUserDTO {

    @NotBlank
    private String username;

    @Size(min = 6, max = 64)
    private String password;
    private String passwordConfirm;
    private String role;
    private String userAboutMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserAboutMe() {
        return userAboutMe;
    }

    public void setUserAboutMe(String userAboutMe) {
        this.userAboutMe = userAboutMe;
    }
}
