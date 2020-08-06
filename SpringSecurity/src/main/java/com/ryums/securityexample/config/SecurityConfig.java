package com.ryums.securityexample.config;

import com.ryums.securityexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/font/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                        .antMatchers("/admin/**").authenticated()
                        .antMatchers("/**").permitAll()
                        .and()

                    .formLogin()
                        .loginPage("/login")
                        .failureHandler(loginFailureHandler)
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .defaultSuccessUrl("/admin/main")
                    .and()

                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                    .and()

        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
