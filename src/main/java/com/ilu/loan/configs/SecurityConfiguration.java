package com.ilu.loan.configs;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ilu.loan.apis.erros.CustomAccessDeniedHandler;
import com.ilu.loan.apis.erros.CustomAuthenticationEntryPoint;
import com.ilu.loan.securities.AuthTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

        private final AuthTokenFilter authTokenFilter;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;

        private final Map<String, String[]> ENDPOINTS = Map.of(
                        "WHITE_LIST", new String[] {
                                        // "/api/v1/auth/**",
                                        // "/swagger-ui/index.html",
                                        // "/v3/api-docs/**",
                                        // "/swagger-ui.html/**",
                                        // "/swagger-ui/**",
                                        "/api/auth/**",
                                        "/swagger-ui/index.html",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html/**",
                                        "/swagger-ui/**",
                                        "/api/customers/**",
                                        "/api/roles",
                                        "/api/test"

                        },
                        "ROLE_CUSTOMER", new String[] {
                                        // "/api/v1/user/**",
                                        "/api/transactions"
                        },
                        "ROLE_ADMIN", new String[] {
                                        "/api/users/**",
                        });

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .httpBasic(AbstractHttpConfigurer::disable)
                                .csrf(AbstractHttpConfigurer::disable)
                                .sessionManagement(
                                                httpSecuritySessionManagementConfigure -> httpSecuritySessionManagementConfigure
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(req -> req
                                                .requestMatchers(ENDPOINTS.get("WHITE_LIST"))
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/instalment-types/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.DELETE, "/api/instalment-types/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/loan-types/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.DELETE, "/api/loan-types/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/transactions/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.DELETE, "/api/transactions/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.PUT, "/api/transactions/{trxId}/pay")
                                                .permitAll()

                                                // .requestMatchers(HttpMethod.PUT, "/api/v1/user").permitAll()

                                                .requestMatchers(HttpMethod.POST, "/api/transactions")
                                                .hasAnyAuthority("ROLE_CUSTOMER")
                                                .requestMatchers(HttpMethod.PUT, "/api/transactions")
                                                .hasAnyAuthority("ROLE_CUSTOMER")
                                                .requestMatchers(HttpMethod.PUT, "/api/transactions/{userId}/approve")
                                                .hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF")
                                                .requestMatchers(HttpMethod.POST, "/api/instalment-types")
                                                .hasAnyAuthority("ROLE_STAFF", "ROLE_ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/instalment-types")
                                                .hasAnyAuthority("ROLE_STAFF", "ROLE_ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/api/loan-types")
                                                .hasAnyAuthority("ROLE_STAFF", "ROLE_ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/loan-types")
                                                .hasAnyAuthority("ROLE_STAFF", "ROLE_ADMIN")
                                                .requestMatchers(ENDPOINTS.get("ROLE_ADMIN"))
                                                .hasAnyAuthority("ROLE_ADMIN")
                                                // .requestMatchers(ENDPOINTS.get("SELLER_LIST"))
                                                // .hasAnyRole("seller", "admin")
                                                // .requestMatchers(ENDPOINTS.get("ADMIN_LIST")).hasAnyRole("admin")
                                                .anyRequest()
                                                .authenticated())
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                                .accessDeniedHandler(customAccessDeniedHandler))
                                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}