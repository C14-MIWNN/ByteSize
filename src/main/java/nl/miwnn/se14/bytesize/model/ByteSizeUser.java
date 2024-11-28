package nl.miwnn.se14.bytesize.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author Heron
 * Defines a user able to log in
 */

@Entity
public class ByteSizeUser implements UserDetails {
    private String ROLE_PREFIX = "ROLE_";

    @Id @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    private String userAboutMe;

    @OneToMany(mappedBy = "recipeCreator", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return list;
    }

    public int generateReferralCode() {
        int code = (int) Math.round((Math.random()* 1000) + 1);
        code = (code * 2) - 1;

        if (role.equals("ADMIN")) {
            code = code * 8;
        }

        return code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


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

    public String getROLE_PREFIX() {
        return ROLE_PREFIX;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setROLE_PREFIX(String ROLE_PREFIX) {
        this.ROLE_PREFIX = ROLE_PREFIX;
    }

    public String getUserAboutMe() {
        return userAboutMe;
    }

    public void setUserAboutMe(String userAboutMe) {
        this.userAboutMe = userAboutMe;
    }
}