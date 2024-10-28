package com.healthy.config;

import com.healthy.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtFilter;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests


                        //Auth
                        .requestMatchers(antMatcher("/auth/**")).permitAll()
                        .requestMatchers(antMatcher("/auth/register/user")).permitAll()
                        .requestMatchers(antMatcher("/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/user/profile")).hasAnyRole("CUSTOMER")
                        .requestMatchers(antMatcher("/user/profile/update")).hasAnyRole("CUSTOMER")

                        //User
                        .requestMatchers(antMatcher("/admin/users/register")).permitAll()


                        //Profile
                        .requestMatchers(antMatcher("/admin/profiles")).permitAll()
                        .requestMatchers(antMatcher("/admin/profiles/page")).permitAll()
                        .requestMatchers(antMatcher("/admin/user/profile")).permitAll()


                        //Habit
                        .requestMatchers(antMatcher("/admin/habits")).permitAll()
                        .requestMatchers(antMatcher("/admin/habits/page")).permitAll()


                        //Habit Type
                        .requestMatchers(antMatcher("/admin/habitTypes")).permitAll()
                        .requestMatchers(antMatcher("/admin/habitTypes/page")).permitAll()


                        //Plan
                        .requestMatchers(antMatcher("admin/plans")).permitAll()
                        .requestMatchers(antMatcher("admin/plans/page")).permitAll()


                        //Goal
                        .requestMatchers(antMatcher("/admin/goals")).permitAll()
                        .requestMatchers(antMatcher("/admin/goals/page")).permitAll()


                        //Tracking Record
                        .requestMatchers(antMatcher("/admin/trackingrecords")).permitAll()
                        .requestMatchers(antMatcher("/admin/trackingrecords/page")).permitAll()


                        //Expert
                        .requestMatchers(antMatcher("/admin/experts")).permitAll()
                        .requestMatchers(antMatcher("/admin/experts/page")).permitAll()


                        //Subscription
                        .requestMatchers(antMatcher("/admin/subscriptions")).permitAll()
                        .requestMatchers(antMatcher("/admin/subscriptions/page")).permitAll()

                        //Resource
                        .requestMatchers(antMatcher("/admin/resources")).permitAll()
                        .requestMatchers(antMatcher("/admin/resources/page")).permitAll()

                        //Profile Resource
                        .requestMatchers(antMatcher("/admin/profile_resources")).permitAll()
                        .requestMatchers(antMatcher("/admin/profile_resources/page")).permitAll()

                        //Sub Plan
                        .requestMatchers(antMatcher("/admin/sub_plans")).permitAll()
                        .requestMatchers(antMatcher("/admin/sub_plans/page")).permitAll()

                        //Permitir hacer opciones después de
                        .anyRequest().authenticated()


                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}