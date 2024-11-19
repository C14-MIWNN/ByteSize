package nl.miwnn.se14.bytesize.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Heron
 * Purpose for the class
 */

@Entity
public class ByteSizeUser implements UserDetails {

    @Id @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "byteSizeUser", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    public int getNumberOfRecipes() {
        int count = 0;
        for (Recipe recipe : recipes) {
            count++;
        }
        return count;
    }

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
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
}