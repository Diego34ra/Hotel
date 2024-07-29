package br.edu.ifgoiano.hotel.security;

import br.edu.ifgoiano.hotel.controller.exception.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    private static final String[] SWAGGER_WHITELIST = {
            "/api/v1/hotel/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/hotel/bookings/{bookingId}/checkIn").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/hotel/bookings/{bookingId}/checkOut").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/hotel/bookings/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "api/v1/hotel/rooms/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/v1/hotel/rooms/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "api/v1/hotel/rooms/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "api/v1/hotel/rooms/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "api/v1/hotel/hospitality/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "api/v1/hotel/hospitality/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "api/v1/hotel/hospitality/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(accessDeniedException()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationEntryPoint accessDeniedException(){
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
