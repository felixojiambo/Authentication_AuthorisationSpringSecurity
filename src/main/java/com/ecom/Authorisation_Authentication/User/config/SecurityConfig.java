package com.ecom.Authorisation_Authentication.User.config;
import com.ecom.Authorisation_Authentication.User.service.AuthenticationService;
import com.ecom.Authorisation_Authentication.User.util.JwtAuthFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh-token",
            "/api/v1/auth/check-email",
            "/api/v1/auth/check-username"
    };

    @Bean
    HttpSecurity filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Disabling CSRF as we use JWT which is immune to CSRF
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(WHITE_LIST_URL).permitAll() //Whitelisting some paths from authentication
                                .anyRequest().authenticated()) // All other requests must be authenticated
 .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class); // Registering our JwtAuthFilter
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new
                DaoAuthenticationProvider();
        provider.setUserDetailsService(authenticationService); // Setting our custom user details service
        provider.setPasswordEncoder(passwordEncoder); // Setting the  password encoder
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
