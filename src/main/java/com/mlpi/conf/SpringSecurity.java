package com.mlpi.conf;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SpringSecurity{

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/resources/static/**").permitAll()
                        .requestMatchers("/resources/static/img/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                                .loginPage("/")
                                .loginProcessingUrl("/login")
//                        .failureForwardUrl("/")
//                                .defaultSuccessUrl("/policy/buyInsurance", true)
                                .defaultSuccessUrl("/dashboard/", true)
                                .permitAll()
                ).logout((logout) -> logout
                .logoutUrl("/logout") // Default is "/logout"
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("JSESSIONID") // Delete cookies
                .permitAll())
//                .logout((logout) -> logout.permitAll())
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }

}
