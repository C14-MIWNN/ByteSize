package nl.miwnn.se14.bytesize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Heron
 * Purpose for the class
 */

@Configuration
@EnableWebSecurity
public class ByteSizeSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/recipe/overview").permitAll()
                .requestMatchers("/webjars/**", "/css/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
