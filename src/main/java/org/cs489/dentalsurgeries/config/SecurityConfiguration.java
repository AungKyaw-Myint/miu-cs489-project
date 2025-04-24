package org.cs489.dentalsurgeries.config;

import lombok.RequiredArgsConstructor;
import org.cs489.dentalsurgeries.auth.Permission;
import org.cs489.dentalsurgeries.auth.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(CsrfConfigurer::disable)
                    .authorizeHttpRequests(
                            authorizeRequests -> authorizeRequests
                                    .requestMatchers("/api/v1/auth/*").permitAll()
//                                    .requestMatchers("/api/v1/auth/authenticate").permitAll()
                                    .requestMatchers("/api/v1/patients/**").hasAnyRole(
                                            UserRole.ADMIN.name(),
                                            UserRole.PATIENT.name())
                                    .requestMatchers("/api/v1/dentists/**").hasAnyRole(
                                            UserRole.ADMIN.name(),
                                            UserRole.DENTIST.name())
                                    .requestMatchers("/api/v1/admins/**").hasAnyRole(
                                            UserRole.ADMIN.name())
                                    .requestMatchers("/api/v1/appointments/**").hasAnyRole(
                                            UserRole.ADMIN.name(),
                                            UserRole.PATIENT.name(),
                                            UserRole.DENTIST.name())
                                    .requestMatchers("/api/v1/patients/search").hasAnyAuthority(
                                            Permission.ADMIN_READ.getPermission(),
                                            Permission.DENTIST_READ.getPermission())
                                    .anyRequest().authenticated())
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .authenticationProvider(authenticationProvider)
                    .sessionManagement(
                            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http. build();
    }
}
