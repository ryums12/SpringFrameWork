package com.ryums.securityexample.config;

import com.ryums.securityexample.service.OauthService;
import com.ryums.securityexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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



@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private AuthenticationFailureHandler loginFailureHandler;
    private LogoutSuccessHandler logoutSuccessHandler;
    private AccessDeniedHandler accessDeniedHandler;
    private PasswordEncoder passwordEncoder;
    private OauthService oauthService;

    @Override
    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/font/**");
        security.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/admin/**").authenticated()
                    .antMatchers("/**").permitAll()
                .and()

                .formLogin()
                    .loginPage("/")
                    .failureHandler(loginFailureHandler)
                    .usernameParameter("id")
                    .passwordParameter("pwd")
                    .defaultSuccessUrl("/admin/main")
                .and()

                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(oauthService)
                    .and()
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
                    .expiredUrl("/")
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}