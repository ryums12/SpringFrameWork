package com.ryums.securityexample.config;

import com.ryums.securityexample.service.UserService;
import lombok.AllArgsConstructor;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private AuthenticationFailureHandler loginFailureHandler;
    private LogoutSuccessHandler logoutSuccessHandler;
    private AccessDeniedHandler accessDeniedHandler;
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

                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .and()

                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                    .and()

                    .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login")
                    .and()
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
