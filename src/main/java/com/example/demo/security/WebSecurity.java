package com.example.demo.security;

import com.example.demo.entity.FamilyRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/family/readChildren").hasRole(FamilyRole.PREMIUM.toString())
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers( "/test").permitAll()
//                .antMatchers( "/favicon.ico").permitAll()
//                .antMatchers( "/user/**").permitAll()
//                .antMatchers("/static/*").permitAll()
//                .antMatchers("/img/*").permitAll()
//                .antMatchers("/css/*").permitAll()
//                .antMatchers("/js/*").permitAll()
//                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
//                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
