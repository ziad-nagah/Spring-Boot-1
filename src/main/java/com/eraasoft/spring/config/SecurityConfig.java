package com.eraasoft.spring.config;

import com.eraasoft.spring.config.filter.AuthFilter;
import com.eraasoft.spring.service.EraaSoftShoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
//    private EraaSoftShoolService
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return  userDetailsManager;
//    }
    @Autowired
    private AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.authorizeHttpRequests(api -> api
//                        .requestMatchers(/*HttpMethod.GET,*/ "/eraa-soft/**").permitAll()//.hasRole("ADMIN")///students
////                      .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/**").permitAll()
                        .requestMatchers(/*HttpMethod.GET,*/ "/auth/**").permitAll().anyRequest().authenticated()
                );

               http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//             http.httpBasic(Customizer.withDefaults());
               http.httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/students").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/students").hasRole("ADMIN");
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withUsername("ahmed").password("{bcrypt}$2a$12$nEx8DYl2BNZhWfq5I0skPOVwu0u.7rR9kj37oL4f09eqKdNqoKeHK").roles("USER","ADMIN").build();
//        return new InMemoryUserDetailsManager(userDetails);
//
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
