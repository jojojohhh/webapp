package com.swlab.webapp.config;

import com.swlab.webapp.config.handler.WebAccessDeniedHandler;
import com.swlab.webapp.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebAccessDeniedHandler webAccessDeniedHandler;
    private final SecurityUserService securityUserService;

    @Autowired
    public WebSecurityConfig(WebAccessDeniedHandler webAccessDeniedHandler, SecurityUserService securityUserService) {
        this.webAccessDeniedHandler = webAccessDeniedHandler;
        this.securityUserService = securityUserService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/login", "/join", "/test").permitAll()
                    .antMatchers("/home/users").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/home", "/home/**").access("hasRole('ROLE_VIEW')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
                    .usernameParameter("email").passwordParameter("password")
                .and()
                    .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                    .exceptionHandling().accessDeniedHandler(webAccessDeniedHandler)
                .and()
                    .authenticationProvider(authenticationProvider()).csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
