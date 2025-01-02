package iuh.fit.dhktpm117ctt.group06.config;


import iuh.fit.dhktpm117ctt.group06.jwt.JwtValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final String[] PUBLIC_URL = { "/api/auth/**", "/api/products/**", "/api/category/**",
            "/api/brand/**", "/api/product-items/**", "/api/cart/**", "/api/orders/**", "/api/feedbacks/**" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(HttpMethod.GET,"/api/cart/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/cart/**").permitAll()
                                .requestMatchers( HttpMethod.POST,"/api/cart/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/product-items").permitAll()
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/api/**").permitAll()

                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/collections/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/collections/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/collections/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/orders/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/brands/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/brands/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/brands/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/product-items/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/product-items/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/product-items/**").hasAnyRole("ADMIN"))
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cfg = new CorsConfiguration();
                        cfg.setAllowedOrigins(Arrays.asList(
                                "http://localhost:2003",
                                "http://localhost:5173",
                                "http://localhost:8080",
                                "http://localhost:8081",
                                "http://54.250.202.79:8080"
                        ));
                        cfg.setAllowedMethods(Collections.singletonList("*"));
                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(Collections.singletonList("Authorization"));
                        cfg.setMaxAge(3600L);
                        return cfg;
                    }
                }))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
