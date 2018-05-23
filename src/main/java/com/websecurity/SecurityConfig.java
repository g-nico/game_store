package com.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomAuthenticationSuccessHandler cash;

    @Autowired
    private CustomAuthenticationEntryPoint caep;

    @Autowired
    private CustomAuthFailureHandler cafh;

    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/purchases/**", "/accounts/**").hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers("/myAccount").authenticated()
                    .antMatchers("/css/**", "/js/**").permitAll()
                    .antMatchers("/login","/resources/**", "/myCart/**").permitAll()
                .and()
                .formLogin()
                    .failureHandler(cafh)
                    .successHandler(cash)
                    .loginPage("/login.html")
                    .loginProcessingUrl("login")
                    .failureUrl("/login")
                    .defaultSuccessUrl("/")
                .and()
/*                .logout()
                    .deleteCookies("gameIds")
                .and()*/
                .httpBasic()
                    .authenticationEntryPoint(caep);
    }
}
